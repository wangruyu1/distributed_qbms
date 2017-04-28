package cn.qtech;

import cn.qtech.rabbitmq.Source;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangruyu
 * @since 2017/4/10-16:19
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy
@EnableBinding(Source.class)
@MapperScan(value = "cn.qtech.mapper")
public class QbmsManagerApplication {
    public static void main(String args[]) {
        new SpringApplicationBuilder(QbmsManagerApplication.class).web(true).run(args);
    }
}
