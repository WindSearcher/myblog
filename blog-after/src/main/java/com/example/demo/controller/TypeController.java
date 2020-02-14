package com.example.demo.controller;


import com.example.demo.entity.Type;
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
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){

        PageHelper.startPage(pageNum,10);
        List<Type> types = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println("pageInfo:"+pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, Model model){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

    @PostMapping("/types/save")
    public String save(Type type, BindingResult result, RedirectAttributes attributes){
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        try{
            typeService.save(type);
        }catch (Exception e){
            attributes.addFlashAttribute("message","操作失败");
        }
        attributes.addFlashAttribute("message","操作成功");
        //这相当于重新输入url
        return "redirect:/admin/types";
    }
}
