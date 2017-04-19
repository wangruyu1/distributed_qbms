package cn.qtech.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/4/18-11:16
 */
@Component
public class ZuulErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext rc = RequestContext.getCurrentContext();
        //TODO:获取异常
        if (rc.getResponse().getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(500);
            rc.setResponseBody("请等待,服务还未起来...");
        }
        return null;
    }
}
