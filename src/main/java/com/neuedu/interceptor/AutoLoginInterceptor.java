package com.neuedu.interceptor;

import com.google.gson.Gson;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AutoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("====preHandle====");

        Cookie[] cookies=request.getCookies();

        if(cookies!=null){

            for(Cookie cookie:cookies){
                String name=cookie.getName();
                if(name.equals("token")){
                     String value=cookie.getValue();
                     //根据token查询用户信息
                    UserInfo userInfo=userService.getUserInfoByToken(value);
                    if(userInfo!=null){
                        request.getSession().setAttribute("currentuser",userInfo);
                        return true;
                    }
                }
            }

        }




        response.reset();
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter=response.getWriter();
        ServerResponse serverResponse=ServerResponse.createServerResponseByError(100,"需要登录");
        Gson gson=new Gson();
        String json= gson.toJson(serverResponse);
        printWriter.write(json);
        printWriter.flush();
        printWriter.close();

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        System.out.println("======postHandle=========");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        System.out.println("======afterCompletion=========");

    }
}
