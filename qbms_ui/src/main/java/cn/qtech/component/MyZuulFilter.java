package cn.qtech.component;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.User;
import cn.qtech.utils.LoginUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangruyu
 * @since 2017/4/8-22:17
 */
@Component
public class MyZuulFilter extends ZuulFilter {
    @Autowired
    private LocalMessageSource localMessageSource;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        System.out.println(request.getServletPath());
//        if(request.getSession(false) != null){
//            System.out.println(request.getSession(false).getId());
//        }
        if (request.getServletPath().contains("login")) {
            return null;
        }
        User user = LoginUtil.getLoginUser();
        if (user == null) {
            try {
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                context.setResponseBody(localMessageSource.getMessage("user.not.login"));
                response.sendRedirect("/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
