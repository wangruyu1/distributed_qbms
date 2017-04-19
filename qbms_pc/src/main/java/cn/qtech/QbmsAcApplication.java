package cn.qtech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wangruyu
 * @since 2017/4/7-19:20
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
//@EnableBinding(Source.class)
@MapperScan(value = "cn.qtech.mapper")
public class QbmsAcApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(QbmsAcApplication.class).web(true).run(args);
    }
}
