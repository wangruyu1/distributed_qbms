package cn.qtech.feign.hystrix;

import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.feign.client.UserPaperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/25-15:40
 */
@Component
public class UserPaperClientHystrix implements UserPaperClient {
    @Override
    public List<UserPaperWithBLOBs> queryCommitedUserPaper(@PathVariable("managerId") String managerId, @PathVariable("status") int status) {
        return new ArrayList<>();
    }
}
