package com.neuedu.aspect;

import com.neuedu.common.ServerResponse;
import com.neuedu.json.ObjectMapperApi;
import com.neuedu.redis.RedisApi;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redis缓存切面类
 * */
@Component
@Aspect
public class RedisCacheAspect {


    @Pointcut("execution(* com.neuedu.service.impl.ProductServiceImpl.*(..) )")
    public  void   pointcut(){}

    @Autowired
    RedisApi redisApi;
    @Autowired
    ObjectMapperApi objectMapperApi;
    @Around("pointcut()")
    public  Object  arround(ProceedingJoinPoint joinPoint){

        Object o=null;
        try {

            //key: Md5(全类名+方法名+参数)
            StringBuffer keyBuffer=new StringBuffer();
            //全类名
            String  className=joinPoint.getTarget().getClass().getName();
            keyBuffer.append(className);
            System.out.println("===className:"+className);
            //获取目标方法的方法名
          String methodName=  joinPoint.getSignature().getName();
          keyBuffer.append(methodName);
            System.out.println("===methodName:"+methodName);
            //方法中的参数
            Object[] objects=joinPoint.getArgs();
             if(objects!=null){
                 for(Object arg:objects){
                     System.out.println("==arg== "+arg);
                     keyBuffer.append(arg);
                 }
             }


             //step1:先读缓存
            String key=keyBuffer.toString();
            String json=redisApi.get(key);
            if(json!=null&& !json.equals("")){
                System.out.println("=============读取到了缓存====");
                return objectMapperApi.str2Obj(json, ServerResponse.class);
            }
            //执行目标方法
           o= joinPoint.proceed();
            System.out.println("=============读取数据库====");
           if(o!=null){
             String  jsoncache= objectMapperApi.obj2str(o);
             redisApi.set(key,jsoncache);
               System.out.println("=============数据库的内容写入缓存====");

           }

        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }


        return o;
    }


}
