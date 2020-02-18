package com.example.demo.controller.admin;


import com.example.demo.entity.Type;
import com.example.demo.entity.User;
import com.example.demo.service.impl.TypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    /*
    * 后台分类首页
    * */
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum,10);
        List<Type> types = typeService.getAllType(user.getId());
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println("pageInfo:"+pageInfo);
        return "admin/types";
    }


    /*
    * 到新增页面
    * */
    @GetMapping("/types/input")
    public String input(){

        return "admin/types-input";
    }

    /*
    * 到修改页面去
    * */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String update(Type type,RedirectAttributes attributes){
        try{
            typeService.updateType(type);
        }catch (Exception e){
            attributes.addFlashAttribute("message","操作失败");
        }
        attributes.addFlashAttribute("message","操作成功");

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, Model model){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

    //新增操作
    @PostMapping("/types/save")
    public String save(Type type, Model model, RedirectAttributes attributes,HttpSession session){
        User user = (User) session.getAttribute("user");

        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            model.addAttribute("message","存在该分类，请勿重复添加");
            return "admin/types-input";
        }
        type.setUserId(user.getId());  //保存分类对应的用户ID
        try{
            typeService.save(type);
        }catch (Exception e){
            attributes.addFlashAttribute("message","分类添加失败");
        }
        attributes.addFlashAttribute("message","分类添加成功");
        //这相当于重新输入url
        return "redirect:/admin/types";
    }
}
