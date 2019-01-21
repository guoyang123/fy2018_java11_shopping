package com.neuedu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志服务切面类
 * */
@Component
@Aspect
public class LogAspect {

    //定义切入点
    @Pointcut("execution(public * com.neuedu.service.impl.ProductServiceImpl.*(..))")
    public  void   pointcut(){}

    //通知: 5中类型

    /**
     *  before:前置通知
     *  after:
     *  afterReturning
     *  afterthrowing
     *
     *  around
     *
     * */
   @Before("pointcut()")
    public   void   before(){
        System.out.println("===============before======");
    }

    @After("pointcut()")
    public   void   after(){
        System.out.println("===============after======");
    }

    @AfterThrowing("pointcut()")
    public   void   afterThrowing(){
        System.out.println("===============afterThrowing======");
    }
    @AfterReturning("pointcut()")
    public   void   AfterReturning(){
        System.out.println("===============AfterReturning======");
    }

   /* @Around("pointcut()")
     public  Object arround(ProceedingJoinPoint proceedingJoinPoint){

        Object o=null;
        try {
            System.out.println("=====arround===before=====");
            //执行切入点匹配的方法
            o=proceedingJoinPoint.proceed();
            System.out.println("=====arround===after=====");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("=====arround===throwing=====");
        }
        System.out.println("=====arround===afterreturning=====");

        return o;
    }*/




}
