package com.example.demo.controller.admin;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Type;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.TypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model){
        PageHelper.startPage(pageNum,10,"createDate desc");
        List<Blog> blogs = blogService.getAllBlog();
        //List<Type> types = typeService.getAllType();
        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
            System.out.println("recommend:"+blog.getRecommend());
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        //model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/blogs";
    }

    @GetMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model){
        PageHelper.startPage(pageNum,10);
        List<Blog> blogs = blogService.getAllBlog();
        List<Type> types = typeService.getAllType();
        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
            System.out.println("recommend:"+type.getName());
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        //返回该页面下的一个片段
        return "admin/blogs :: blogList";
    }

    @PostMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id){
        blogService.deleteBlog(id);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.getAllType());
        return "admin/blogs-input";
    }

    /*新增文章*/
    @PostMapping("/blogs")
    public String post(Blog blog, BindingResult result, RedirectAttributes attributes){

        //blog.setUser();
        System.out.println("htmlContent:"+blog.getContent());
        blog.setTypeId(blog.getType().getId());
        try{
            blogService.saveBlog(blog);
        }catch (Exception e){
            attributes.addFlashAttribute("message","文章添加失败");
        }
        attributes.addFlashAttribute("message","文章添加成功");

        //返回后台页面redirect:/admin/types
        return "redirect:/admin/blogs";
    }
}
