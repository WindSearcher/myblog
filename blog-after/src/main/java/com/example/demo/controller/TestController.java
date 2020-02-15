package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @PostMapping("/test")
    public void test(HttpServletRequest request){
        System.out.println("html:"+request.getParameter("editormd-html-textarea"));
    }
}
