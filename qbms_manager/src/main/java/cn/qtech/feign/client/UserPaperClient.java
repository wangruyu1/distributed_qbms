package cn.qtech.feign.client;

import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.feign.hystrix.UserPaperClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/25-15:39
 */
@FeignClient(value = "qbms-user", fallback = UserPaperClientHystrix.class)
public interface UserPaperClient {
    @RequestMapping(value = "userpapers/{managerId}/{status}", method = RequestMethod.GET)
    public List<UserPaperWithBLOBs> queryCommitedUserPaper(@PathVariable("managerId") String managerId, @PathVariable("status") int status);
}
