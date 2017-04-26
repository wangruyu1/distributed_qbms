package cn.qtech.feign.client;

import cn.qtech.domain.User;
import cn.qtech.domain.data.UserData;
import cn.qtech.feign.hystrix.UserClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/10-16:22
 */
@FeignClient(name = "qbms-pc", fallback = UserClientHystrix.class)
public interface UserClient {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getCurrentUser();

    @RequestMapping(value = "/users/ids", method = RequestMethod.POST)
    public List<User> queryUsersByBatchIds(@RequestParam(value = "userIds") List<String> userIds);

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getIds();

    @RequestMapping(value = "/user/names", method = RequestMethod.GET)
    public List<UserData> queryUserNamesByBatchUserIds(@RequestParam("userIds") List<String> userIds);
}
