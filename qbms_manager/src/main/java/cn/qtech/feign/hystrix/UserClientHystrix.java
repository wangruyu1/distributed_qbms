package cn.qtech.feign.hystrix;

import cn.qtech.domain.User;
import cn.qtech.feign.client.UserClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/10-16:23
 */
@Component
public class UserClientHystrix implements UserClient {

    @Override
    public List<User> queryUsersByBatchIds(List<String> userIds) {
        return new ArrayList<>();
    }
    @Override
    public User getIds() {
        return new User();
    }
}
