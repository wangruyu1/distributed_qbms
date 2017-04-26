package cn.qtech.feign;

import cn.qtech.domain.dto.PaperDTO;
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
    @RequestMapping(value = "/paper/{id}", method = RequestMethod.GET)
    public PaperDTO queryPaperById(@PathVariable("id") String paperId);
}
