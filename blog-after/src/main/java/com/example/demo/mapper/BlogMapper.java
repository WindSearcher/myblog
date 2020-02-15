package com.example.demo.mapper;

import com.example.demo.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blog where id = #{id}")
    public Blog getBlog(@Param("id") Long id);


    @Select("select * from blog")
    public List<Blog> getAllBlog();

    @Select("select * from blog where typeId = #{typeId}")
    public List<Blog> getAllTypeBlog(@Param("typeId") Long typeId);



    @Insert("insert into blog(title,content,firstPicture,flag,views,appreciation,shareStatement,commentabled,recommend,typeId,createDate,updateDate) values(#{title},#{content},#{firstPicture},#{flag},#{views},#{appreciation},#{shareStatement},#{commentabled},#{recommend},#{typeId},#{createDate},#{updateDate})")
    public void saveBlog(Blog blog);

    @Update("update blog set title=#{title},content=#{content},firstPicture=#{firstPicture},flag=#{flag},views=#{views},appreciation=#{appreciation},shareStatement=#{shareStatement},commentabled=#{commentabled},recommend=#{recommend},createTime=#{createTime},updateTime=#{updateTime},typeId=#{typeId} where id = #{id}")
    public void updateBlog(@Param("id") Long id,Blog blog);


    @Delete("delete from blog where id = #{id}")
    public void deleteBlog(@Param("id") Long id);




}
