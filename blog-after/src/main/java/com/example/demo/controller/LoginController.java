package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Resource
    ValueOperations<String ,Object> valueOperations;

    @GetMapping("/")
    public String loginPage(){
        return "admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
        User user = userService.checkUser(username,password);
        if(user == null){
            return "admin/login";
        }else{
            //如果存在该用户，则生成token
            String token = GenerateToken.getToken(user);
            String key = token.split("\\.")[2];
            //.是一个转义字符,
            valueOperations.set(key,token, 7, TimeUnit.DAYS);
            return "admin/index";
        }

    }
}
