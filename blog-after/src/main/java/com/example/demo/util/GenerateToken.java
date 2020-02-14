package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;


/*
 * @Author:liqiang
 * @Date:2020-02-14 13:09
 * @Description:生成token
 * */
public class GenerateToken {

    public static String getToken(User user) {
        String token="";
        /*
         * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，
         * 唯一密钥的话可以保存在服务端。withAudience()存入需要保存在
         * token的信息，这里我把用户ID存入token中，我可以自定义一个密钥
         * 密钥由密码和系统时间来的
         * */
        token= JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()+System.currentTimeMillis()));
        return token;
    }
}
