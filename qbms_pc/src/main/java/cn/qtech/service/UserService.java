package cn.qtech.service;

import cn.qtech.commopent.LocalMessageSource;
import cn.qtech.domain.Role;
import cn.qtech.domain.User;
import cn.qtech.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangruyu
 * @since 2017/3/14-18:24
 */
@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private LocalMessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userMapper.queryByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(messageSource.getMessage("database.connection.error"));
        }
        if (user == null) {
            throw new UsernameNotFoundException(messageSource.getMessage("user.login.failed"));
        }
        //获取用户菜单
        List<String> roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        user.setMenus(menuService.queryTreeMenusByRoleIds(roleIds));
        return user;
    }

    public List<User> queryUsersByBatchIds(List<String> ids) {
        return userMapper.queryUsersByBatchIds(ids);
    }

    public boolean modifyPassword(String name, String password) {
        return userMapper.modifyPassword(name, password) > 0;
    }
}
