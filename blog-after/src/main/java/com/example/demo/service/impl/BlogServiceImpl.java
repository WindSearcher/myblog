package com.example.demo.service.impl;

import com.example.demo.entity.Blog;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    //根据分类号查询
    public List<Blog> getAllTypeBlog(Long typeId){
        return blogMapper.getAllTypeBlog(typeId);
    }

    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        blog.setViews(0);

        blogMapper.saveBlog(blog);
    }

    @Override
    public void updateBlog(Long id, Blog blog) {
        Blog b = blogMapper.getBlog(id);
        if(b == null){
            throw new NotFoundException("该博客不存在");
        }
        blogMapper.updateBlog(id,blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }
}
