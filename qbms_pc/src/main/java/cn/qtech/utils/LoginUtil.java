package cn.qtech.utils;

import cn.qtech.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wangruyu
 * @since 2017/3/15-09:58
 */
public class LoginUtil {
    private static SecurityContext context = SecurityContextHolder.getContext();
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUtil.class);
    public static User getLoginUser() {
        LOGGER.info(Thread.currentThread().getName());
        if (context == null) {
            return null;
        }
        if (context.getAuthentication() == null || context.getAuthentication().getPrincipal() == null) {
            return null;
        }
        if (context.getAuthentication().getPrincipal() instanceof UserDetails) {
            return (User) context.getAuthentication().getPrincipal();
        }
        return null;
    }
}
