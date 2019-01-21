package com.neuedu.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志服务切面类
 * */
@Component
@Aspect
public class LogAspect {



    //定义切入点表达式
    @Pointcut("execution(public * com.neuedu.service.impl.UserServiceImpl.*(..))")
    public  void   test(){}



    //通知: 5中类型

    @Before("test()")
    public   void   before(){
        System.out.println("===============before======");
    }

    @After("test()")
    public   void   after(){
        System.out.println("===============after======");
    }


}
