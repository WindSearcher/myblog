package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where blogId=#{blogId} order by createTime")
    public List<Comment> listCommentByBlogId(@Param("blogId") Long blogId);

    //根据文章ID找到顶级的评论节点
    @Select("select * from comment where blogId=#{blogId} and parentCommentId=-1")
    public List<Comment> getTopCommentList(@Param("blogId") Long blogId);

    //根据评论ID得到回复评论
    @Select("select * from comment where parentCommentId=#{id}")
    public List<Comment> getReplyComments(@Param("id") Long id);

    @Transactional
    @Insert("insert into comment(nickname,avatar,createTime,blogId,parentCommentId,content,adminComment) values(#{nickname},#{avatar},#{createTime},#{blogId},#{parentCommentId},#{content},#{adminComment})")
    public void saveComment(Comment comment);
}
