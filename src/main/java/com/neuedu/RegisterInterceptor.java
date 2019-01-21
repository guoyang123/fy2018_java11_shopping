package com.neuedu;


import com.neuedu.interceptor.AutoLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class RegisterInterceptor  implements WebMvcConfigurer {

    @Autowired
    AutoLoginInterceptor autoLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //            /portal/*  --> /portal/a  portal/b
        //  /portal/**
        //自动登录   /portal/user/get_information.do
        //           /portal/cart/add.do ...
        //           /portal/order/create.do ...

        List<String> excludeList=new ArrayList<String>();
        excludeList.add("/portal/user/login.do");
        excludeList.add("/portal/user/register.do");
        excludeList.add("/portal/user/logout.do");
        excludeList.add("/portal/product/**");
       // registry.addInterceptor(autoLoginInterceptor).addPathPatterns("/portal/**").excludePathPatterns(excludeList);



    }
}
