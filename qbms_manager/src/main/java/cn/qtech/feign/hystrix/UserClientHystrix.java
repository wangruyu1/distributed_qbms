package cn.qtech.feign.hystrix;

import cn.qtech.domain.User;
import cn.qtech.domain.data.UserData;
import cn.qtech.feign.client.UserClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Override
    public List<UserData> queryUserNamesByBatchUserIds(@RequestParam("userIds") List<String> userIds) {
        return new ArrayList<>();
    }
}
