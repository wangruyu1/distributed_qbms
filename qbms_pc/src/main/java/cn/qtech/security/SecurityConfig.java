package cn.qtech.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        LoginFailedHandler loginFailedHandler = new LoginFailedHandler();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
        http.authorizeRequests()
//                .antMatchers("/pc/logout").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailedHandler)
                .and().logout().permitAll()
                .and().csrf().disable()
        ;

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
