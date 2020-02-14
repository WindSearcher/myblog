package com.example.demo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.demo.entity.User;
import com.example.demo.selfAnnotation.LoginToken;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Resource
    ValueOperations<String ,Object> valueOperations;


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        System.out.println("token:"+token);

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();


        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken userLoginToken = method.getAnnotation(LoginToken.class);
            if (userLoginToken.required()) {

                // 执行认证，没有token，故不在登陆状态
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);

                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }

                //获取用户信息
                User user = userService.findUserById(Integer.parseInt(userId));

                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }

                /*从redis去token，如果失效，返回错误码*/
                //验证token，这里可以用redis来拓展，通过在redis中设置过期时间，不使用自带的token过期验证
                //对于这个，我使用无限期有效，不设置期限可能导致用户信息泄露，也可以设置七天免密登陆

                String token1 = valueOperations.get(token.split("\\.")[2]).toString();
                System.out.println("token1:"+token1);
                if(token1 == null){
                    throw new RuntimeException("token无效，请重新登录获取token");
                }

                /*验证服务端token和客户端token是否一致*/
                return true;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
