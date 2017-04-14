package cn.qtech;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author wangruyu
 * @since 2017/4/8-14:53
 */
@SpringCloudApplication
@EnableFeignClients
@EnableEurekaClient
@EnableZuulProxy
public class QbmsUiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(QbmsUiApplication.class).web(true).run(args);
    }
}
