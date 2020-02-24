package com.example.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Column(name = "id",isKey = true,type= MySqlTypeConstant.BIGINT,isAutoIncrement = true)
    private Long id;


    @Column(name = "blogId",type= MySqlTypeConstant.BIGINT)
    private Long blogId;

    @Column(name = "parentCommentId",type= MySqlTypeConstant.BIGINT)
    private Long parentCommentId;

    @Column(name = "nickname",type = MySqlTypeConstant.CHAR)
    private String nickname;

    @Column(name = "avatar",type = MySqlTypeConstant.CHAR)
    private String avatar;

    @Column(name = "createTime",type = MySqlTypeConstant.DATETIME)
    private Date createTime;

    @Column(name = "content",type = MySqlTypeConstant.VARCHAR)
    private String content;

    //1表示是博主身份
    @Column(name = "adminComment",type = MySqlTypeConstant.INT)
    private Boolean adminComment;


    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

}
