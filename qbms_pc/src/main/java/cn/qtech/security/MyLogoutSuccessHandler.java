package cn.qtech.security;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.fegin.HtmlClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangruyu
 * @since 2017/4/23-11:11
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private LocalMessageSource localMessageSource;
    @Autowired
    private HtmlClient htmlClient;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        BaseMessage baseMessage = new BaseMessage(200, true, localMessageSource.getMessage("user.logout.success"));
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(baseMessage));
    }
}
