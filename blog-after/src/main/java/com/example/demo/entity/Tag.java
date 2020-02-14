package com.example.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {

    @Column(name = "id",isKey = true,type= MySqlTypeConstant.BIGINT,isAutoIncrement = true)
    private Long id;

    @Column(name = "name",type = MySqlTypeConstant.CHAR)
    private String name;

    private List<Blog> blogs = new ArrayList<>();

}
