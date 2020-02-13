package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControoler {

    @GetMapping("/")
    public String index(){
//        String blog = null;
//        if(blog == null){
//            throw new NotFoundException("博客找不到");
//        }else

        return "index";
    }
}
