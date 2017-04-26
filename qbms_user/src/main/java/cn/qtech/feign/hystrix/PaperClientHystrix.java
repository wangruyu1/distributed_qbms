package cn.qtech.feign.hystrix;

import cn.qtech.domain.dto.PaperDTO;
import cn.qtech.feign.PaperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangruyu
 * @since 2017/4/19-18:16
 */
@Component
public class PaperClientHystrix implements PaperClient {
    @Override
    public PaperDTO queryPaperById(@PathVariable("paperId") String paperId) {
        return new PaperDTO();
    }
}
