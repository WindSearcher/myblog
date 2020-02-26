package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Type;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.TypeServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){

        //这里由于不是做博客系统，没有开放注册接口，只是用来给自己写博客的
        List<Type> types = typeService.getAllType(1L);
        Long userId = 1L;

        if(id == -1){
            //根据这个id查询博客分页
            id = types.get(0).getId();
        }

        for(Type type : types){
            List<Blog> blogList = blogService.getAllTypeBlog(type.getId(),userId);
            type.setBlogs(blogList);
        }

        PageHelper.startPage(pageNum,10,"createDate desc");
        List<Blog> blogs = blogService.getAllTypeBlog(id,userId);
        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
            //将user的信息给blog
            blog.setUser(userService.findUserById(userId));
        }

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);


        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);

        System.out.println("--------------activeTypeId-----------:"+id);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
