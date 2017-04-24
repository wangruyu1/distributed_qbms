package cn.qtech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author wangruyu
 * @since 2017/3/14-18:11
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationProvider authenticationProvider;
    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登出处理
//        new SecurityContextLogoutHandler().logout(request,response,SecurityContextHolder.getContext().getAuthentication());
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        LoginFailedHandler loginFailedHandler = new LoginFailedHandler();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
//        登录
        http.authorizeRequests()
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
                .sessionManagement()
                .maximumSessions(1)
//                .sessionRegistry(sessionRegistry());
        ;
//      登出
        http.logout()
//                logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("SESSION")
                .clearAuthentication(true)
        ;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
