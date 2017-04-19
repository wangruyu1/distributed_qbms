package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.UserPaper;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.service.UserPaperService;
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

    @RequestMapping(value = "/userPapers/userId={id}", method = RequestMethod.GET)
    public List<UserPaper> queryAllByUserId(@PathVariable("id") String userId, @RequestParam("status") int status) {
        return userPaperService.queryAllByUserIdAndStatus(userId, status);
    }

    @RequestMapping(value = "/userPaper/{id}", method = RequestMethod.PUT)
    public BaseMessage commitAnswer(@RequestParam("content") String content, @PathVariable("paperId") String paperId) {
        if(userPaperService.modifyContent(paperId,content)){
            return new BaseMessage(200,true,localMessageSource.getMessage("userpaper.commit.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("userpaper.commit.failed"));
    }
}