package cn.qtech.commopent;

import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author wangruyu
 * @since 2017/4/12-19:45
 */
//@Configuration
public class CookieConfig {
    public DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("/");
//        defaultCookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return defaultCookieSerializer;
    }
}
