package com.example.demo.service;

import com.example.demo.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    public Blog getBlog(Long id);

    public Blog getAndConvert(Long id);

    public List<Blog> getAllBlog(Long userId);

    //最新推荐
    public List<Blog> getAllRecommendBlog();

    //根据分类ID查询查询某博主下的所有博文
    public List<Blog> getAllTypeBlog(Long typeId,Long userId);

    public void saveBlog(Blog blog);

    public void updateBlog(Blog blog);

    public void deleteBlog(Long id);

    public Map<String,List<Blog>> archiveBlog();

    public Long countBlog();
}
