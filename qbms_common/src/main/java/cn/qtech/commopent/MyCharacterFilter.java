package cn.qtech.commopent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wangruyu
 * @since 2017/3/15-16:56
 */
@Component
public class MyCharacterFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyCharacterFilter.class);
    @Value("${web.encoding}")
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("初始化characterEncoding:" + encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=" + encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
