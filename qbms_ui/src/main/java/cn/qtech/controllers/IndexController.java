package cn.qtech.controllers;

import cn.qtech.domain.User;
import cn.qtech.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wangruyu
 * @since 2017/3/15-11:07
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public ModelAndView home() {
        User user = LoginUtil.getLoginUser();
        if (user == null) {
            return new ModelAndView("/login");
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(HttpSession session, HttpServletRequest request) {
        User user = LoginUtil.getLoginUser();
        return user;
    }
}
