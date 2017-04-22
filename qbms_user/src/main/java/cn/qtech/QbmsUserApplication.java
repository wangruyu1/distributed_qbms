package cn.qtech;

import cn.qtech.rabbitmq.Source;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangruyu
 * @since 2017/4/17-15:32
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
@EnableBinding(Source.class)
@MapperScan(value = "cn.qtech.mapper")
public class QbmsUserApplication {
    public static void main(String args[]) {
        new SpringApplicationBuilder(QbmsUserApplication.class).web(true).run(args);
    }
}
