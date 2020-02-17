package com.example.demo.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    /*
    * 在访问之前拦截
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---------拦截---------");
        if(request.getSession().getAttribute("user") == null){
            //重定向到admin页面
            System.out.println("重定向");
            response.sendRedirect("/admin");
            //false表示不往下执行
            return false;
        }

        return true;
    }
}
