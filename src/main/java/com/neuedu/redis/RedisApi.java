package com.neuedu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisApi {

    @Autowired
    private JedisPool jedisPool;

    /**
     * @param  key
     * @param value
     * */

    public  String  set(String key,String value){

        String result=null;
        Jedis jedis=null;

        try{
             jedis=jedisPool.getResource();
            result= jedis.set(key, value);
        }catch (Exception e){
             jedisPool.returnBrokenResource(jedis);
        }finally {
           if(jedis!=null){
               jedisPool.returnResource(jedis);
           }
        }
        return result;
    }

    /**
     * @param  key
     * @param value
     * @param second
     * */

    public  String  setex(String key,int second,String value){

        String result=null;
        Jedis jedis=null;

        try{
            jedis=jedisPool.getResource();
            result= jedis.setex(key, second, value);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }


        return result;
    }

    /**
     * @param  key
     *
     * */

    public  String  get(String key){

        String result=null;
        Jedis jedis=null;

        try{
            jedis=jedisPool.getResource();
            result= jedis.get(key);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }


        return result;
    }

    /**
     * @param  key
     *
     * */

    public  Long  del(String key){

        Long result=null;
        Jedis jedis=null;

        try{
            jedis=jedisPool.getResource();
            result= jedis.del(key);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }


        return result;
    }
    /**
     * @param  key
     * @param second
     * */

    public  Long  set(String key,int second){

        Long result=null;
        Jedis jedis=null;

        try{
            jedis=jedisPool.getResource();
            result= jedis.expire(key, second);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }


        return result;
    }

}
