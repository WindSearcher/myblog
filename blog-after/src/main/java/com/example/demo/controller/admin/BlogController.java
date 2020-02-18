package com.example.demo.controller.admin;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Type;
import com.example.demo.entity.User;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.TypeServiceImpl;
import com.example.demo.util.BuildArticleTabloidUtil;
import com.example.demo.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;


    /*博客列表分页展示*/
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model,HttpSession session){
        PageHelper.startPage(pageNum,10,"createDate desc");
        User user = (User) session.getAttribute("user");
        List<Blog> blogs = blogService.getAllBlog(user.getId());
        //List<Type> types = typeService.getAllType();
        for(Blog blog : blogs){
            Type type = typeService.getType(blog.getTypeId());
            blog.setType(type);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);

        return "admin/blogs";
    }

    @GetMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model){
        return " ";
    }

    /*删除文章*/
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        try{
            blogService.deleteBlog(id);
        }catch(Exception e){
            attributes.addFlashAttribute("message","删除失败，修改数据库出现bug");
        }
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    /*跳转到新增文章页面*/
    @GetMapping("/blogs/input")
    public String input(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("types",typeService.getAllType(user.getId()));
        return "admin/blogs-input";
    }

    /*新增文章*/
    @PostMapping("/blogs")
    public String post(Blog blog, BindingResult result, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId()); //保存文章对应的用户ID
        blog.setTypeId(blog.getType().getId()); //保存文章对应的分类ID
        String description = BuildArticleTabloidUtil.buildArticleTabloid(MarkdownUtils.markdownToHtml(blog.getContent()));

        System.out.println("----文章摘要----："+ description);
        blog.setDescription(description);
        try{
            blogService.saveBlog(blog);
        }catch (Exception e){
            attributes.addFlashAttribute("message","文章添加失败");
        }
        attributes.addFlashAttribute("message","文章添加成功");

        //返回后台页面redirect:/admin/types
        return "redirect:/admin/blogs";
    }

    /*跳转到编辑文章页面*/
    @GetMapping("/blogs/{id}/update")
    public String update(@PathVariable Long id, Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("types",typeService.getAllType(user.getId()));
        return "admin/blogs-update";
    }

    /*编辑文章保存*/
    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog, BindingResult result, RedirectAttributes attributes, HttpSession session){

        blog.setTypeId(blog.getTypeId()); //保存文章对应的分类ID
        try{
            blogService.updateBlog(blog);
        }catch (Exception e){
            attributes.addFlashAttribute("message","文章修改失败");
        }
        attributes.addFlashAttribute("message","文章修改成功");

        //返回后台页面redirect:/admin/types
        return "redirect:/admin/blogs";
    }

}
