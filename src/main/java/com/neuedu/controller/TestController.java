package com.neuedu.controller;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.json.ObjectMapperApi;
import com.neuedu.pojo.UserInfo;
import com.neuedu.redis.RedisApi;
import com.neuedu.redis.RedisProperties;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/portal")
public class TestController {


    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    RedisProperties redisProperties;



    @RequestMapping(value = "/user/{userid}")
    public ServerResponse<UserInfo> findUser(@PathVariable Integer userid, HttpSession session){


        System.out.println(redisProperties.getMaxIdle());



        UserInfo u= userInfoMapper.selectByPrimaryKey(userid);

        if(u!=null){
            return ServerResponse.createServerResponseBySuccess(null,u);
        }else{
            return ServerResponse.createServerResponseByError("fail");
        }
    }



    @Autowired
    private  JedisPool jedisPool;

    @RequestMapping(value = "/redis")
    public  String  getJedis(){

       Jedis jedis= jedisPool.getResource();
       String value= jedis.set("root","root1");

       jedisPool.returnResource(jedis);


       return value;
    }

    @Autowired
    private RedisApi redisApi;
    @RequestMapping(value = "/key/{key}")
    public  String  getkey(@PathVariable("key") String key){

     String value= redisApi.get(key);


        return value;
    }

    @Autowired
    ObjectMapperApi objectMapperApi;
    @RequestMapping(value = "/json/{userid}")
    public ServerResponse<UserInfo> findUserByJson(@PathVariable Integer userid, HttpSession session){


        UserInfo u= userInfoMapper.selectByPrimaryKey(userid);
        String json=objectMapperApi.obj2str(u);
        System.out.println("u======="+json);

        return ServerResponse.createServerResponseBySuccess(null,u);
    }
    @RequestMapping(value = "/jsonPretty/{userid}")
    public ServerResponse<UserInfo> findUserByJsonPretty(@PathVariable Integer userid, HttpSession session){


        UserInfo u= userInfoMapper.selectByPrimaryKey(userid);
        List<UserInfo> userInfoList=new ArrayList<UserInfo>();
        userInfoList.add(u);
        String json=objectMapperApi.obj2strPretty(userInfoList);
        List list= objectMapperApi.str2Obj(json, new TypeReference<List<UserInfo>>() {});
        System.out.println("u======="+list);


        return ServerResponse.createServerResponseBySuccess(null,u);
    }



}
