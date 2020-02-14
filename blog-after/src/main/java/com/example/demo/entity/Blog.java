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
@Table(name="blog")
public class Blog {

    @Column(name = "id",isKey = true,type= MySqlTypeConstant.BIGINT,isAutoIncrement = true)
    private Long id;

    //标题
    @Column(name = "title",type = MySqlTypeConstant.CHAR)
    private String title;

    //内容
    @Column(name = "content",type = MySqlTypeConstant.CHAR)
    private String content;

    //首图
    @Column(name = "firstPicture",type = MySqlTypeConstant.CHAR)
    private String firstPicture;

    //原创，转载，翻译
    @Column(name = "flag",type = MySqlTypeConstant.CHAR)
    private String flag;

    //浏览次数
    @Column(name = "views",type = MySqlTypeConstant.INT)
    private Integer views;

    //是否 开启赞赏
    @Column(name = "appreciation",type = MySqlTypeConstant.INT)
    private boolean appreciation;

    //是否开启转载声明
    @Column(name = "shareStatement",type = MySqlTypeConstant.INT)
    private boolean shareStatement;

    @Column(name = "commentabled",type = MySqlTypeConstant.INT)
    private boolean commentabled;

    @Column(name = "published",type = MySqlTypeConstant.INT)
    private boolean published;

    @Column(name = "recommend",type = MySqlTypeConstant.INT)
    private boolean recommend;

    @Column(name = "createTime",type = MySqlTypeConstant.DATETIME)
    private Date createTime;

    @Column(name = "updateTime",type = MySqlTypeConstant.DATETIME)
    private Date updateTime;

    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();


}
