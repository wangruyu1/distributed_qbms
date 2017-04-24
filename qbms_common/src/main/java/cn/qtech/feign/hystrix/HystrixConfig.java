package cn.qtech.feign.hystrix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangruyu
 * @since 2017/4/23-18:12
 */
@Configuration
public class HystrixConfig {
    @Bean
    public UserClietnHystrix userClietnHystrix() {
        return new UserClietnHystrix();
    }
}
