package cn.qtech;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wangruyu
 * @since 2017/4/7-14:45
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceManagerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceManagerApplication.class).web(true).run(args);
    }

}
