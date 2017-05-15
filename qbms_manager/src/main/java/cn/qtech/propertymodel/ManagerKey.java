package cn.qtech.propertymodel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/4/28-12:01
 */
@Component
@ConfigurationProperties(prefix = "rediskey.manager")
public class ManagerKey {
    private String redisSingalManagerPapsersKey;
    private Integer expireTime;

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getRedisSingalManagerPapsersKey() {
        return redisSingalManagerPapsersKey;
    }

    public void setRedisSingalManagerPapsersKey(String redisSingalManagerPapsersKey) {
        this.redisSingalManagerPapsersKey = redisSingalManagerPapsersKey;
    }
}
