package com.neuedu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAspect {
    @Pointcut("execution(public * com.neuedu.service.impl.ProductServiceImpl.findAll())")
    public void cache(){}
    @Around("cache()")
    public Object around(ProceedingJoinPoint joinPoint){
        //获取方法的参数
        Object o=null;
        try {
            System.out.println("============前执行=====");
            o=joinPoint.proceed();
            System.out.println("============执行完成=====");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }

}
