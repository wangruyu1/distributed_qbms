package cn.qtech;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangruyu
 * @since 2017/4/8-14:53
 */
@SpringCloudApplication
@EnableFeignClients
@EnableEurekaClient
@EnableZuulProxy
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class QbmsUiApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(QbmsUiApplication.class).web(true).run(args);
    }
}
