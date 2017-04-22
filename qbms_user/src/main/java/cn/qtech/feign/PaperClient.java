package cn.qtech.feign;

import cn.qtech.domain.Paper;
import cn.qtech.feign.hystrix.PaperClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangruyu
 * @since 2017/4/19-18:11
 */
@FeignClient(value = "qbms-manager", fallback = PaperClientHystrix.class)
public interface PaperClient {
    @RequestMapping(value = "/paper/{paperId}", method = RequestMethod.GET)
    public Paper queryPaperById(@PathVariable("paperId") String paperId);
}
