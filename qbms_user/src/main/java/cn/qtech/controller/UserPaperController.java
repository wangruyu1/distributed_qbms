package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.constant.UserPaperStatus;
import cn.qtech.domain.User;
import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.feign.PaperClient;
import cn.qtech.service.UserPaperService;
import cn.qtech.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/userpapers/status={status}", method = RequestMethod.GET)
    public List<UserPaperWithBLOBs> queryAllByUserId(@PathVariable("status") int status) {
        User user = LoginUtil.getLoginUser();
        String userId = user.getId();
        return userPaperService.queryAllByUserIdAndStatus(userId, status);
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
}
