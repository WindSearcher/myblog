package com.example.demo.mapper;

import com.example.demo.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blog where id = #{id}")
    public Blog getBlog(@Param("id") Long id);


    @Select("select * from blog where userId = #{userId}")
    public List<Blog> getAllBlog(@Param("userId") Long userId);

    //根据分类ID和用户ID查询博客
    @Select("select * from blog where typeId = #{typeId} and userId=#{userId}")
    public List<Blog> getAllTypeBlog(@Param("typeId") Long typeId,@Param("userId") Long userId);

    @Select("select * from blog where recommend = 1 order by createDate desc limit 0,6")
    public List<Blog> getAllRecommendBlog();

    @Insert("insert into blog(title,content,firstPicture,flag,views,appreciation,shareStatement,commentabled,recommend,typeId,userId,description,createDate,updateDate) values(#{title},#{content},#{firstPicture},#{flag},#{views},#{appreciation},#{shareStatement},#{commentabled},#{recommend},#{typeId},#{userId},#{description},#{createDate},#{updateDate})")
    public void saveBlog(Blog blog);

    @Update("update blog set title=#{title},content=#{content},firstPicture=#{firstPicture},flag=#{flag},views=#{views},appreciation=#{appreciation},shareStatement=#{shareStatement},commentabled=#{commentabled},recommend=#{recommend},updateTime=#{updateTime},typeId=#{typeId},description=#{description} where id = #{id}")
    public void updateBlog(Blog blog);

    @Update("update blog set views=#{views} where id=#{id}")
    public void updateViews(@Param("views") Integer views,@Param("id") Long id);

    @Delete("delete from blog where id = #{id}")
    public void deleteBlog(@Param("id") Long id);

    @Select("select date_format(createDate,'%Y') as year from blog group by year order by year desc")
    public List<String> findGroupYear();

    @Select("select * from blog where date_format(createDate,'%Y') = #{year}")
    public List<Blog> findBlogByYear(@Param("year") String year);

    @Select("select count(id) from blog")
    public Long countBlog();
}
