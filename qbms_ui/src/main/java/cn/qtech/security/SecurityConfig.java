package cn.qtech.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/3/14-18:11
 */
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
        http.sessionManagement().invalidSessionUrl("/login.html");
        http.authorizeRequests()
                .antMatchers("/login", "/pc/login", "/controllers/loginController.js").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().csrf().disable()
        ;

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
