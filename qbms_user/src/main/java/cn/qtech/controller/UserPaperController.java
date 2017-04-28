package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.constant.UserPaperStatus;
import cn.qtech.domain.User;
import cn.qtech.domain.UserPaperData;
import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.domain.data.UserData;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.domain.dto.PaperDTO;
import cn.qtech.domain.dto.UserPaperDTO;
import cn.qtech.feign.PaperClient;
import cn.qtech.feign.client.UserClient;
import cn.qtech.service.UserPaperService;
import cn.qtech.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangruyu
 * @since 2017/4/17-15:54
 */
@RestController
public class UserPaperController {
    @Autowired
    private UserPaperService userPaperService;
    @Autowired
    private LocalMessageSource localMessageSource;
    @Autowired
    private PaperClient paperClient;
    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/userpapers/status={status}", method = RequestMethod.GET)
    public List<UserPaperDTO> queryAllByUserId(@PathVariable("status") int status) {
        User user = LoginUtil.getLoginUser();
        String userId = user.getId();
        List<UserPaperDTO> rtnData = new ArrayList<>();
        List<UserPaperWithBLOBs> userPapers = userPaperService.queryAllByUserIdAndStatus(userId, status);
        if (userPapers == null || userPapers.size() == 0) {
            return null;
        }
        List<String> userIds = userPapers.stream().map(UserPaperWithBLOBs::getManagerId).collect(Collectors.toList());
        List<UserData> userDatas = userClient.queryUserNamesByBatchUserIds(userIds);
        Map<String, String> userNameMap = userDatas.stream().collect(Collectors.toMap(UserData::getUserId, UserData::getUserName));
        userPapers.forEach(userPaper -> {
            UserPaperDTO tmp = new UserPaperDTO();
            tmp.setTotalTime(userPaper.getTotalTime());
            tmp.setStartTime(userPaper.getStartTime());
            tmp.setManagerId(userPaper.getManagerId());
            tmp.setManagerName(userNameMap.get(userPaper.getManagerId()));
            tmp.setName(userPaper.getName());
            tmp.setTitle(userPaper.getTitle());
            tmp.setScore(userPaper.getScore());
            tmp.setUserPaperId(userPaper.getId());
            rtnData.add(tmp);
        });
        return rtnData;
    }

    @RequestMapping(value = "userpaper/{id}", method = RequestMethod.GET)
    public UserPaperData queryUserPaperData(@PathVariable("id") String userPaperId) {
        UserPaperWithBLOBs userPaper = userPaperService.queryUserPaperById(userPaperId);
        PaperDTO paperDTO = paperClient.queryPaperById(userPaper.getPaperId());
        UserPaperData data = new UserPaperData();
        data.setUserPaperId(userPaper.getId());
        data.setContent(userPaper.getContent());
        data.setUserAnswer(userPaper.getAnswer());
        data.setRightAnswer(paperDTO.getAnswer());
        return data;
    }

    @RequestMapping(value = "userpapers/{managerId}/{status}", method = RequestMethod.GET)
    public List<UserPaperWithBLOBs> queryUserPaperByManagerAndStatus(@PathVariable("managerId") String managerId, @PathVariable("status") int status) {
        return userPaperService.queryUserPaperByManagerAndStatus(managerId, status);
    }

    @RequestMapping(value = "/userPaper/{id}", method = RequestMethod.PUT)
    public BaseMessage commitAnswer(@PathVariable("id") String paperId, @RequestBody UserPaperWithBLOBs userPaper) {
        userPaper.setStatus(UserPaperStatus.COMMITED.value());
        if (userPaperService.modifyUserPaperForCommit(userPaper)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("userpaper.commit.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("userpaper.commit.failed"));
    }

    @RequestMapping(value = "userPaper/{id}", method = RequestMethod.POST)
    public BaseMessage gradeForUser(@PathVariable("id") String userPaperId, @RequestParam("score") int score) {
        if (userPaperService.grade(userPaperId, score, UserPaperStatus.FINISHED.value())) {
            return new BaseMessage(200, true, localMessageSource.getMessage("user.grade.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("user.grade.failed"));
    }
}
