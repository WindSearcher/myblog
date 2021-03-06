package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Type;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.CommentServiceImpl;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexControoler {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        Long userId = 1L;
        PageHelper.startPage(pageNum,10,"createDate desc");
        List<Blog> blogs = blogService.getAllBlog(userId);
        List<Type> types = typeService.getTopType(6);
        List<Blog> recommendedBlogs = blogService.getAllRecommendBlog();
        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
            //将user的信息给blog
            blog.setUser(userService.findUserById(userId));
        }

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        for(Type type : types){
            List<Blog> blogList = blogService.getAllTypeBlog(type.getId(),userId);
            type.setBlogs(blogList);
        }
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs",recommendedBlogs);
        return "index";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model, HttpSession session){
        Blog blog = blogService.getAndConvert(id);

        blog.setUser(userService.findUserById(blog.getUserId()));
        blog.setType(typeService.getType(blog.getTypeId()));
        model.addAttribute("blog",blog);
        return "blog";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
