package cn.qtech.security;

import cn.qtech.domain.User;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.utils.LoginUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 * @author wangruyu
 * @since 2017/3/15-09:57
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = LoginUtil.getLoginUser();
        System.out.println(request.getSession(false).getId());
        LOGGER.info(MessageFormat.format("用户{0}登录成功...",user.getName()));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(new BaseMessage(200,true,"登陆成功"));
        printWriter.print(result);
    }
}
