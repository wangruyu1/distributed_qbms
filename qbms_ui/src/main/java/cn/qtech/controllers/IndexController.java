package cn.qtech.controllers;

import cn.qtech.constant.UserType;
import cn.qtech.domain.User;
import cn.qtech.exception.AppException;
import cn.qtech.feign.UserClient;
import cn.qtech.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangruyu
 * @since 2017/3/15-11:07
 */
@Controller
public class IndexController {
    @Autowired
    private UserClient userClient;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public ModelAndView home() throws AppException {
        User user = userClient.getCurrentUser();
        if (user == null) {
            return new ModelAndView("/login");
        }
        if (user.getType() == UserType.MANAGER.value()) {
            return new ModelAndView("index_manager");
        } else if (user.getType() == UserType.USER.value()) {
            return new ModelAndView("index");
        } else if (user.getType() == UserType.ADMIN.value()) {
            return new ModelAndView("index_admin");
        }

        throw new AppException("unknow.error");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        return LoginUtil.getLoginUser();
    }
}
