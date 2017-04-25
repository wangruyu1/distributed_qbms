package cn.qtech.controller;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.User;
import cn.qtech.domain.data.UserData;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.service.UserService;
import cn.qtech.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/16-15:39
 */
@RestController
public class UserController {
    @Autowired
    private LocalMessageSource localMessageSource;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User queryUserInfo() {
        return LoginUtil.getLoginUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public BaseMessage modifyPassword(@RequestParam("password") String password) {
        User user = LoginUtil.getLoginUser();
        if (userService.modifyPassword(user.getName(), password)) {
            return new BaseMessage(200, true, localMessageSource.getMessage("user.password.modify.success"));
        }
        return new BaseMessage(201, false, localMessageSource.getMessage("user.password.modify.failed"));
    }

    @RequestMapping(value = "user/password", method = RequestMethod.POST)
    public BaseMessage checkPassword(@RequestParam("password") String password) {
        User user = LoginUtil.getLoginUser();
        if (user == null) {
            return new BaseMessage(401, false, localMessageSource.getMessage("user.not.login"));
        }
        if (!user.getPassword().equals(password)) {
            return new BaseMessage(402, false, localMessageSource.getMessage("user.passowrd.check.failed"));
        }
        request.getSession(false).removeAttribute("lockScreen");
        return new BaseMessage(200, true, localMessageSource.getMessage("user.password.check.success"));
    }

    @RequestMapping(value = "user/{id}/username", method = RequestMethod.GET)
    public String queryUsernameById(@PathVariable("id") String userId) {
        return LoginUtil.getLoginUser().getName();
    }

    @RequestMapping(value = "users/ids", method = RequestMethod.POST)
    public List<User> queryUsersByBatchIds(@RequestParam("userIds") List<String> userIds) {
        return userService.queryUsersByBatchIds(userIds);
    }
    @RequestMapping(value = "/user/names", method = RequestMethod.GET)
    public List<UserData> queryUserNamesByBatchUserIds(@RequestParam("userIds")List<String> userIds){
        return userService.queryUserNamesByBatchUserIds(userIds);
    }
}
