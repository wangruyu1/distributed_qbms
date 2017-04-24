package cn.qtech.fegin.hystrix;

import cn.qtech.fegin.HtmlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangruyu
 * @since 2017/4/23-15:10
 */
@Component
public class HtmlClientHystrix implements HtmlClient {
    @Override
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
