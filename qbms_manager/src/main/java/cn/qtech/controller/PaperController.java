package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.constant.UserPaperStatus;
import cn.qtech.domain.*;
import cn.qtech.domain.data.UserData;
import cn.qtech.domain.dto.*;
import cn.qtech.exception.AppException;
import cn.qtech.feign.client.UserClient;
import cn.qtech.feign.client.UserPaperClient;
import cn.qtech.propertymodel.ManagerKey;
import cn.qtech.rabbitmq.RabbitSender;
import cn.qtech.service.*;
import cn.qtech.utils.LoginUtil;
import cn.qtech.utils.RandomNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangruyu
 * @since 2017/3/27-15:27
 */
@RestController
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectCategoryService subjectCategoryService;
    @Autowired
    private LocalMessageSource localMessageSource;
    @Autowired
    private PaperCategoryService paperCategoryService;
    @Autowired
    private SubjectDifficultyService subjectDifficultyService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private MakePaperService makePaperService;
    @Autowired
    private ManagerUserService managerUserService;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private UserPaperClient userPaperClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ManagerKey managerKey;

    @RequestMapping(value = "papers", method = RequestMethod.GET)
    public List<PaperDTO> queryAll() {
        User user = LoginUtil.getLoginUser();
        StringBuilder redisKey = new StringBuilder(user.getId());
        redisKey.append(managerKey.getRedisSingalManagerPapsersKey());
        //读取redis数据
        ValueOperations<String, List<PaperDTO>> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(redisKey.toString())) {
            return valueOperations.get(redisKey.toString());
        }
        List<PaperDTO> rtnData = new ArrayList<>();
        List<Paper> papers = paperService.queryAll();
        if (papers == null || papers.size() == 0) {
            return null;
        }

        List<String> categoryIds = papers.stream().map(Paper::getPaperCategoryId).collect(Collectors.toList());
        List<String> difficultyIds = papers.stream().map(Paper::getPaperDifficultyId).collect(Collectors.toList());
        List<String> userIds = papers.stream().map(Paper::getUserId).collect(Collectors.toList());

        List<PaperCategory> categories = paperCategoryService.queryCategoryByBatchIds(categoryIds);
        List<SubjectDifficulty> difficulties = subjectDifficultyService.queryDifficultiesByBatchIds(difficultyIds);
        List<User> users = userClient.queryUsersByBatchIds(userIds);
        User u = userClient.getIds();

        Map<String, String> categoryNames = categories.stream().collect(Collectors.toMap(PaperCategory::getCategoryId, PaperCategory::getName));
        Map<String, String> difficultyNames = difficulties.stream().collect(Collectors.toMap(SubjectDifficulty::getSubjectId, SubjectDifficulty::getName));
        Map<String, String> userNames = users.stream().collect(Collectors.toMap(User::getId, User::getName));
        papers.forEach(p -> {
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setName(p.getName());
            paperDTO.setDifficultyName(difficultyNames.get(p.getPaperDifficultyId()));
            paperDTO.setCategoryName(categoryNames.get(p.getPaperCategoryId()));
            paperDTO.setName(p.getName());
            paperDTO.setContent(p.getContent());
            paperDTO.setCreateTime(p.getCreateTime());
            paperDTO.setPaperId(p.getPaperId());
            paperDTO.setTitle(p.getTitle());
            paperDTO.setUserId(p.getUserId());
            paperDTO.setUserName(userNames.get(p.getUserId()));
            rtnData.add(paperDTO);
        });
        //将数据放入缓存
        valueOperations.set(redisKey.toString(), rtnData);
        return rtnData;
    }

    @RequestMapping(value = "/userpapers/commited", method = RequestMethod.GET)
    public List<UserPaperDTO> queryCommitedUserPapers() {
        User user = LoginUtil.getLoginUser();
        String userId = user.getId();
        //读取redis数据
//        StringBuilder redisKey = new StringBuilder(userId);
//        redisKey.append("-papersForCorrect");
//        ValueOperations<String, List<UserPaperDTO>> valueOperations = redisTemplate.opsForValue();
//        if (redisTemplate.hasKey(redisKey.toString())) {
//            return valueOperations.get(redisKey.toString());
//        }
        List<UserPaperDTO> rtnData = new ArrayList<>();
        List<UserPaperWithBLOBs> userPapers = userPaperClient.queryCommitedUserPaper(userId, UserPaperStatus.COMMITED.value());
        if (userPapers == null || userPapers.size() == 0) {
            return null;
        }
        //查询用户名,前台汉化
        List<String> userIds = userPapers.stream().map(UserPaperWithBLOBs::getUserId).collect(Collectors.toList());
        List<UserData> userDatas = userClient.queryUserNamesByBatchUserIds(userIds);
        Map<String, String> userNameMap = userDatas.stream().collect(Collectors.toMap(UserData::getUserId, UserData::getUserName));
        //查询正确答案
        List<String> userPaperIds = userPapers.stream().map(UserPaperWithBLOBs::getPaperId).collect(Collectors.toList());
        List<Paper> papers = paperService.queryPapersByBatchPaperIds(userPaperIds);
        Map<String, String> paperRightAnswerMap = new HashMap<>();
        papers.forEach(paper -> {
            paperRightAnswerMap.put(paper.getPaperId(), this.getPaperDetail(paper.getContent()).getAnswer());
        });
        userPapers.forEach(userPaper -> {
            UserPaperDTO tmp = new UserPaperDTO();
            tmp.setPaperId(userPaper.getPaperId());
            tmp.setUserPaperId(userPaper.getId());
            tmp.setUserPaperName(userPaper.getName());
            tmp.setUserPaperTitle(userPaper.getTitle());
            tmp.setUserAnswer(userPaper.getAnswer());
            tmp.setStartTime(userPaper.getStartTime());
            tmp.setUserId(userPaper.getUserId());
            tmp.setUserName(userNameMap.get(userPaper.getUserId()));
            tmp.setTotalTime(userPaper.getTotalTime());
            tmp.setRightAnswer(paperRightAnswerMap.get(userPaper.getPaperId()));
            rtnData.add(tmp);
        });
//        valueOperations.set(redisKey.toString(), rtnData);
        return rtnData;
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.GET)
    public PaperDTO queryPaperDetail(@PathVariable("id") String paperId) {
        PaperDTO rtnData = new PaperDTO();
        Paper paper = paperService.queryById(paperId);
        String content = paper.getContent();
        if (StringUtils.isEmpty(content)) {
            return rtnData;
        }
        //查询试题信息
        PaperDTO temp = this.getPaperDetail(paper.getContent());
        rtnData.setContent(temp.getContent());
        rtnData.setAnswer(temp.getAnswer());
        return rtnData;
    }

    @RequestMapping(value = "paper", method = RequestMethod.POST)
    public BaseMessage addPaper(@RequestBody Paper paper) {
        if (paperService.insert(paper)) {
            //操作数据库成功,删除缓存.
            this.deleteKeyForModifyPaper();
            return new BaseMessage(200, true, localMessageSource.getMessage("paper.add.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.add.failed"));
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.PUT)
    public BaseMessage modifyPaper(@PathVariable("id") String id, @RequestBody Paper paper) {
        if (paperService.update(paper)) {
            //操作数据库成功,删除缓存.
            this.deleteKeyForModifyPaper();
            return new BaseMessage(200, true, localMessageSource.getMessage("paper.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.modify.failed"));
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.DELETE)
    public BaseMessage deletePaper(@PathVariable("id") String id) {
        if (paperService.delete(id)) {
            //操作数据库成功,删除缓存.
            this.deleteKeyForModifyPaper();
            return new BaseMessage(200, true, localMessageSource.getMessage("paper.delete.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.delete.failed"));
    }

    @RequestMapping(value = "paper/baseinfo", method = RequestMethod.POST)
    public Paper addBaseinfoForPaper(@RequestBody Paper paper) throws AppException {
        User user = LoginUtil.getLoginUser();
        if (user == null) {
            throw new AppException("user.not.login");
        }
        paper.setUserId(user.getId());
        paper.setPaperId(UUID.randomUUID().toString());
        paper.setCreateTime(new Date());
        if (paperService.addBaseinfoForPaper(paper)) {
            return paperService.queryById(paper.getPaperId());
        }
        throw new AppException("paper.add.baseinfo.failed");
    }

    @RequestMapping(value = "paper/{id}/subject", method = RequestMethod.POST)
    public BaseMessage addSubjectForPaper(@RequestBody SubjectDTO subjectDTO) throws AppException {
        User user = LoginUtil.getLoginUser();
        if (user == null) {
            throw new AppException("user.not.login");
        }
        //TODO:()
        SubjectWithBLOBs subject = new SubjectWithBLOBs();
        subject.setUserId(subjectDTO.getUserId());
        subject.setName(subjectDTO.getName());
        subject.setSubjectCategoryId(subjectDTO.getCategoryId());
        subject.setSubjectDifficultyId(subjectDTO.getDifficultyId());
        subject.setContent(subjectDTO.getContent());
        subject.setAnswer(subjectDTO.getAnswer());
        //试题存在
        if (subjectDTO.getSubjectId() != null && !"".equals(subjectDTO.getSubjectId())) {
            subject.setSubjectId(subjectDTO.getSubjectId());
        } else {
            //添加试题
            subject.setSubjectId(UUID.randomUUID().toString());
            if (!subjectService.insert(subject)) {
                throw new AppException("subject.add.failed");
            }
        }

        //添加试卷信息
        if (subjectDTO.isFirstSubject()) {
            subjectDTO.setContent("|" + subjectDTO.getScore() + "#" + subject.getSubjectId());
        } else {
            subjectDTO.setContent("," + subject.getSubjectId());
        }
        Paper paper = paperService.queryById(subjectDTO.getPaperId());
        if (paper.getContent() == null) {
            paper.setContent("");
        }
        if (paperService.addContent(subjectDTO.getPaperId(), paper.getContent() + subjectDTO.getContent())) {
            //试题成功添加到试卷才删除缓存.
            this.deleteKeyForModifyPaper();
            return new BaseMessage(200, true, "subject.add.success");
        }
        return new BaseMessage(201, false, "subject.add.failed");
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.POST)
    public BaseMessage addPaperAutomatic(@RequestBody SubjectDTO subjectDTO) throws AppException {
        if (subjectDTO.getPaperId() == null || subjectDTO.getPaperId().equals("")) {
            throw new AppException("paper.not.exist");
        }
        //判断试题库是否有足够试题
        int subjectNum = subjectService.getCountByCategoryAndDifficulty(subjectDTO.getCategoryId(), subjectDTO.getDifficultyId());
        if (subjectNum < subjectDTO.getLittleSubjectNum()) {
            return new BaseMessage(201, false, "试题库只有" + subjectNum + "道题,出题失败");
        }
        //随机选取题目
        List<SubjectWithBLOBs> subjects = subjectService.queryAllByCategoryAndDifficulty(subjectDTO.getCategoryId(), subjectDTO.getDifficultyId());
        int[] selectedIndexs = RandomNumberUtil.getDifferentNumbers(subjectDTO.getLittleSubjectNum(), subjectNum);
        StringBuilder contentStr = new StringBuilder();
        contentStr.append("|");
        contentStr.append(subjectDTO.getScore());
        contentStr.append("#");
        for (int i = 0; i < selectedIndexs.length; i++) {
            contentStr.append(subjects.get(i).getSubjectId());
            if (i == selectedIndexs.length - 1) {
                continue;
            }
            contentStr.append(",");
        }
        Paper paper = paperService.queryById(subjectDTO.getPaperId());
        if (!paperService.addContent(subjectDTO.getPaperId(), paper.getContent() + contentStr)) {
            //试题成功添加到试卷才删除缓存.
            this.deleteKeyForModifyPaper();
            return new BaseMessage(201, false, localMessageSource.getMessage("subject.add.failed"));
        }
        //添加数据库成功,删除缓存.
        this.deleteKeyForModifyPaper();
        return new BaseMessage(200, true, localMessageSource.getMessage("subject.add.success"));
    }

    @RequestMapping("paper/{paperId}/makepaper")
    public BaseMessage makePaper(@PathVariable("paperId") String paperId, @RequestBody MakePaper makePaper) {
        User user = LoginUtil.getLoginUser();
        makePaper.setUserId(user.getId());
        makePaper.setCreateTime(new Date());
        makePaper.setId(UUID.randomUUID().toString());
        if (makePaperService.insert(makePaper)) {
            //通过rabbitmq通知user模块添加试卷信息.
            List<String> userIds = managerUserService.queryUserIdsByManagerId(user.getId());
            MakePaperDTO makePaperDTO = new MakePaperDTO();
            Paper paper = paperService.queryById(paperId);
            PaperDTO paperDto = this.getPaperDetail(paper.getContent());
            paper.setContent(paperDto.getContent());
            makePaperDTO.setPaper(paper);
            makePaperDTO.setManagerId(user.getId());
            makePaperDTO.setMakePaper(makePaper);
            makePaperDTO.setUserIds(userIds);
            if (rabbitSender.sendMessage(makePaperDTO)) {
                return new BaseMessage(200, true, localMessageSource.getMessage("paper.make.success"));
            }
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.make.failed"));
    }

    private String getSubjectOrder(int order) {
        switch (order) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            default:
                return "需要添加题号";
        }
    }

    private PaperDTO getPaperDetail(String content) {
        String[] subjectIds = content.split("\\|");
        List<String> subjectIdList = Arrays.asList(subjectIds).subList(1, subjectIds.length);
        StringBuilder bigSubjectContent = new StringBuilder();
        StringBuilder bigSubjectAnswer = new StringBuilder();
        PaperDTO paper = new PaperDTO();
        for (int i = 0; i < subjectIdList.size(); i++) {
            String id = subjectIdList.get(i);
            //获取每题分数
            String[] scoreIds = id.split("#");
            int score = Integer.parseInt(scoreIds[0]);
            List<String> realIdList = Arrays.asList(scoreIds[1].split(","));
            //查询试题
            List<SubjectWithBLOBs> subjects = subjectService.querySubjectsByBatch(realIdList);
            StringBuilder contentStr = new StringBuilder();
            contentStr.append("第");
            contentStr.append(this.getSubjectOrder(i + 1));
            contentStr.append("题:");
            if (subjects.size() > 0) {
                SubjectCategory subjectCategory = subjectCategoryService.queryById(subjects.get(0).getSubjectCategoryId());
                contentStr.append(subjectCategory.getName());
            }
            contentStr.append("(每题");
            contentStr.append(score);
            contentStr.append("分)\r\n");
            StringBuilder answerStr = new StringBuilder();
            answerStr.append(contentStr);
            for (int j = 0; j < subjects.size(); j++) {
                SubjectWithBLOBs subject = subjects.get(j);
                contentStr.append(j + 1);
                contentStr.append(".");
                contentStr.append(subject.getName());
                contentStr.append("\r\n");
                contentStr.append(subject.getContent());
                contentStr.append("\r\n");
                //答案
                answerStr.append(j + 1);
                answerStr.append(":");
                answerStr.append(subject.getAnswer());
                answerStr.append("\r\n");
            }
            bigSubjectContent.append(contentStr);
            bigSubjectAnswer.append(answerStr);
            paper.setContent(bigSubjectContent.toString());
            paper.setAnswer(bigSubjectAnswer.toString());
        }
        return paper;
    }

    private void deleteKeyForModifyPaper() {
        User user = LoginUtil.getLoginUser();
        StringBuilder redisKey = new StringBuilder(user.getId());
        redisKey.append(managerKey.getRedisSingalManagerPapsersKey());
        if (!redisTemplate.hasKey(redisKey.toString())) {
            return;
        }
        //读取redis数据
        ValueOperations<String, List<PaperDTO>> valueOperations = redisTemplate.opsForValue();
        redisTemplate.delete(redisKey.toString());
    }

}
