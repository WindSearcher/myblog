package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Type;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.TypeServiceImpl;
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
public class IndexControoler {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,10,"createDate desc");
        List<Blog> blogs = blogService.getAllBlog();
        List<Type> types = typeService.getTopType(6);

        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
        }

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        for(Type type : types){
            List<Blog> blogList = blogService.getAllTypeBlog(type.getId());
            type.setBlogs(blogList);
        }
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog",blog);
        model.addAttribute("type",typeService.getType(blog.getTypeId()));
        return "blog";
    }

}
