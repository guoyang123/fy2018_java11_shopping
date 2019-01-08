package com.neuedu.controller;

import com.neuedu.common.RedisPool;
import com.neuedu.common.RedisProperties;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
public class TestController {


    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/user/{userid}")
    public ServerResponse<UserInfo> findUser(@PathVariable Integer userid){

        UserInfo u= userInfoMapper.selectByPrimaryKey(userid);

        if(u!=null){
            return ServerResponse.createServerResponseBySuccess(null,u);
        }else{
            return ServerResponse.createServerResponseByError("fail");
        }
    }

    @Autowired
    RedisProperties redisProperties;
    @RequestMapping(value = "/config")
    public String testRedisConfig(){
        return  redisProperties.getMaxIdle()+"";
    }
    @Autowired
    JedisPool jedisPool;
    @RequestMapping(value = "/pool")
    public String getJedis(){
        return  jedisPool.getResource().toString();
    }
}
