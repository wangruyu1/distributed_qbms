package cn.qtech.fegin;

import cn.qtech.fegin.hystrix.HtmlClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangruyu
 * @since 2017/4/23-15:10
 */
@FeignClient(name = "qbms-ui", fallback = HtmlClientHystrix.class)
public interface HtmlClient {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login();
}
