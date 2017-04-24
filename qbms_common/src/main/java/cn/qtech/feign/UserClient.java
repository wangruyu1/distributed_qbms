package cn.qtech.feign;

import cn.qtech.domain.User;
import cn.qtech.feign.hystrix.UserClietnHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangruyu
 * @since 2017/4/23-17:44
 */
@FeignClient(value = "qbms-pc", fallback = UserClietnHystrix.class)
public interface UserClient {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getCurrentUser();
}
