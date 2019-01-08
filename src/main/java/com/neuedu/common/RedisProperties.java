package com.neuedu.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class RedisProperties {

    @Value("${redis.max.total}")
    private Integer maxTotal;
            // 最大空闲数
      @Value("${redis.max.idle}")
   private Integer  maxIdle;
            // 最小空闲数
            @Value("${redis.min.idle}")
    private Integer minIdle;
    @Value("${redis.ip}")
    private String ip;
    @Value("${redis.port}")
    private Integer port;
            //在获取实例时，校验实例是否有效
            @Value("${redis.test.borrow}")
    private Boolean testBorrow;
            //在把jedis实例放回到连接池是，检查实例是否有效
            @Value("${redis.test.return}")
    private Boolean testReturn;




}
