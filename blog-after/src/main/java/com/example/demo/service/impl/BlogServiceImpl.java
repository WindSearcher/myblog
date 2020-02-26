package com.example.demo.service.impl;

import com.example.demo.entity.Blog;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.service.BlogService;
import com.example.demo.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        //需要转化markdown成html代码
        return blogMapper.getBlog(id);
    }

    @Override
    public Blog getAndConvert(Long id){
        Blog blog = blogMapper.getBlog(id);
        if(blog == null){
            throw new NotFoundException("博客不存在");
        }
        blogMapper.updateViews(blog.getViews()+1,blog.getId());

        Blog b = new Blog();
        //做一个深复制
        BeanUtils.copyProperties(blog,b);
        b.setViews(b.getViews()+1);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        return b;
    }

    //根据用户ID查找所有文章
    @Override
    public List<Blog> getAllBlog(Long userId) {
        return blogMapper.getAllBlog(userId);
    }


    //最新推荐
    @Override
    public List<Blog> getAllRecommendBlog(){
        return blogMapper.getAllRecommendBlog();
    }

    //根据分类号查询
    public List<Blog> getAllTypeBlog(Long typeId,Long userId){
        return blogMapper.getAllTypeBlog(typeId,userId);
    }

    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        blog.setViews(0);

        blogMapper.saveBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {

        blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public Map<String,List<Blog>> archiveBlog(){

        List<String> years = blogMapper.findGroupYear();
        Map<String,List<Blog>> map = new HashMap<>();

        for(String year : years){
            map.put(year,blogMapper.findBlogByYear(year));
        }

        return map;
    }

    @Override
    public Long countBlog(){
        return blogMapper.countBlog();
    }
}
