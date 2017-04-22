package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.*;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.domain.dto.MakePaperDTO;
import cn.qtech.domain.dto.PaperDTO;
import cn.qtech.domain.dto.SubjectDTO;
import cn.qtech.exception.AppException;
import cn.qtech.feign.client.UserClient;
import cn.qtech.rabbitmq.RabbitSender;
import cn.qtech.service.*;
import cn.qtech.utils.LoginUtil;
import cn.qtech.utils.RandomNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "papers", method = RequestMethod.GET)
    public List<PaperDTO> queryAll() {
        System.out.println("user:" + LoginUtil.getLoginUser());
        List<PaperDTO> rtnData = new ArrayList<>();
        List<Paper> papers = paperService.queryAll();

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
        String[] subjectIds = content.split("\\|");
        List<String> subjectIdList = Arrays.asList(subjectIds).subList(1, subjectIds.length);
        StringBuilder bigSubjectContent = new StringBuilder();
        StringBuilder bigSubjectAnswer = new StringBuilder();
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
        }

        rtnData.setContent(bigSubjectContent.toString());
        rtnData.setAnswer(bigSubjectAnswer.toString());
        return rtnData;
    }

    @RequestMapping(value = "paper", method = RequestMethod.POST)
    public BaseMessage addPaper(@RequestBody Paper paper) {
        if (paperService.insert(paper)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("paper.add.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.add.failed"));
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.PUT)
    public BaseMessage modifyPaper(@PathVariable("id") String id, @RequestBody Paper paper) {
        if (paperService.update(paper)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("paper.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("paper.modify.failed"));
    }

    @RequestMapping(value = "paper/{id}", method = RequestMethod.DELETE)
    public BaseMessage deletePaper(@PathVariable("id") String id) {
        if (paperService.delete(id)) {
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
        SubjectWithBLOBs subject = new SubjectWithBLOBs();
        subject.setUserId(subject.getUserId());
        subject.setName(subjectDTO.getName());
        subject.setSubjectCategoryId(subjectDTO.getCategoryId());
        subject.setSubjectDifficultyId(subjectDTO.getDifficultyId());
        subject.setContent(subjectDTO.getContent());
        subject.setAnswer(subjectDTO.getAnswer());
        //试题存在
        if (subjectDTO.getSubjectId() == null || subjectDTO.getSubjectId().equals("")) {
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
            return new BaseMessage(201, false, localMessageSource.getMessage("subject.add.failed"));
        }
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
            makePaperDTO.setPaper(paperService.queryById(paperId));
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

}
