package cn.qtech.security;

import cn.qtech.domain.dto.BaseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangruyu
 * @since 2017/3/15-10:10
 */
public class LoginFailedHandler implements AuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFailedHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        System.out.println(request.getSession(false).getId());
        if (exception instanceof InternalAuthenticationServiceException) {
            printWriter.print(objectMapper.writeValueAsString(new BaseMessage(401, false, exception.getMessage())));
        } else if (exception instanceof UsernameNotFoundException || exception instanceof AuthenticationException) {
            printWriter.print(objectMapper.writeValueAsString(new BaseMessage(401, false, "用户名或者密码不正确")));
        } else {
            printWriter.print(objectMapper.writeValueAsString(new BaseMessage(402, false, "网络错误")));
        }
        printWriter.close();
    }
}
