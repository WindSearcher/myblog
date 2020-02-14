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
@Table(name = "user")
public class User {

    @Column(name = "id",isKey = true,type= MySqlTypeConstant.BIGINT,isAutoIncrement = true)
    private Long id;

    @Column(name = "nickname",type = MySqlTypeConstant.CHAR)
    private String nickname;

    @Column(name = "username",type = MySqlTypeConstant.CHAR)
    private String username;

    @Column(name = "password",type = MySqlTypeConstant.CHAR)
    private String password;

    @Column(name = "email",type = MySqlTypeConstant.CHAR)
    private String email;

    @Column(name = "type",type = MySqlTypeConstant.INT)
    private Integer type;

    @Column(name = "createTime",type = MySqlTypeConstant.DATETIME)
    private Date createTime;

    @Column(name = "updateTime",type = MySqlTypeConstant.DATETIME)
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();
}
