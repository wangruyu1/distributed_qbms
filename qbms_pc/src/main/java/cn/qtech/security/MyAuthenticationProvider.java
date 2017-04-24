package cn.qtech.security;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/4/23-22:14
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private LocalMessageSource messageSource;
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails user = userService.loadUserByUsername(token.getName());
        if (!user.getPassword().equals(token.getCredentials())) {
            throw new BadCredentialsException(messageSource.getMessage("user.username.or.password.not.correct"));
        }
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> Class) {
        return true;
    }
}
