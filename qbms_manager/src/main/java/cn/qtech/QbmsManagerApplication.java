package cn.qtech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wangruyu
 * @since 2017/4/10-16:19
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@MapperScan(value = "cn.qtech.mapper")
public class QbmsManagerApplication {
    public static void main(String args[]) {
        new SpringApplicationBuilder(QbmsManagerApplication.class).web(true).run(args);
    }
}
