package com.example.demo.service;

import com.example.demo.entity.Blog;

import java.util.List;

public interface BlogService {

    public Blog getBlog(Long id);

    public Blog getAndConvert(Long id);

    public List<Blog> getAllBlog();

    //根据分类ID查询
    public List<Blog> getAllTypeBlog(Long typeId);

    public void saveBlog(Blog blog);

    public void updateBlog(Long id,Blog blog);

    public void deleteBlog(Long id);


}
