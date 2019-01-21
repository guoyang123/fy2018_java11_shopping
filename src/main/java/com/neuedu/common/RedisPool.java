package com.neuedu.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@Component
@Configuration
public class RedisPool {
    @Autowired
    private   RedisProperties redisProperties;
    @Bean
    public  JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setTestOnReturn(redisProperties.getTestReturn());
        jedisPoolConfig.setTestOnBorrow(redisProperties.getTestBorrow());
        jedisPoolConfig.setBlockWhenExhausted(true);

       return new JedisPool(jedisPoolConfig,redisProperties.getIp(),redisProperties.getPort(),2000,"Qinfo20180507@",0);
    }

}
