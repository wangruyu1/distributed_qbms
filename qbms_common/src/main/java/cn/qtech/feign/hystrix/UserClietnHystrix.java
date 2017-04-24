package cn.qtech.feign.hystrix;

import cn.qtech.domain.User;
import cn.qtech.feign.UserClient;

/**
 * @author wangruyu
 * @since 2017/4/23-17:47
 */
public class UserClietnHystrix implements UserClient {
    @Override
    public User getCurrentUser() {
        return new User();
    }
}

