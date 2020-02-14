package com.example.demo.selfAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/*
 * @Author:liqiang
 * @Date:2020-02-14 12:00
 * @Description:加上此注解，则需要登陆才能进行操作，这里针对于方法级别进行拦截
 * */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginToken {
    boolean required() default true;
}
