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

    //分类id
    @Column(name = "typeId",type= MySqlTypeConstant.BIGINT)
    private Long typeId;

    //用户ID
    @Column(name = "userId",type = MySqlTypeConstant.BIGINT)
    private Long userId;

    //内容
    @Column(name = "content",type = MySqlTypeConstant.TEXT)
    private String content;

    //描述
    @Column(name = "description",type = MySqlTypeConstant.CHAR)
    private String description;

    //首图
    @Column(name = "firstPicture",type = MySqlTypeConstant.CHAR)
    private String firstPicture;

    //原创，转载，翻译
    @Column(name = "flag",type = MySqlTypeConstant.CHAR)
    private String flag;

    //浏览次数
    @Column(name = "views",type = MySqlTypeConstant.INT,defaultValue = "0")
    private Integer views;

    //是否 开启赞赏，默认开启
    @Column(name = "appreciation",type = MySqlTypeConstant.INT,defaultValue = "1")
    private Boolean appreciation;

    //是否开启转载声明
    @Column(name = "shareStatement",type = MySqlTypeConstant.INT,defaultValue = "1")
    private Boolean shareStatement;

    //是否开启评论
    @Column(name = "commentabled",type = MySqlTypeConstant.INT,defaultValue = "1")
    private Boolean commentabled;

    @Column(name = "published",type = MySqlTypeConstant.INT)
    private Boolean published;

    //推荐，需要手动开启
    @Column(name = "recommend",type = MySqlTypeConstant.INT,defaultValue = "0")
    private Boolean recommend;

    @Column(name = "createDate",type = MySqlTypeConstant.DATETIME)
    private Date createDate;

    @Column(name = "updateDate",type = MySqlTypeConstant.DATETIME)
    private Date updateDate;

    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();


}
