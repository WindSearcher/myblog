package com.example.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type")
public class Type {

    @Column(name = "id",isKey = true,type= MySqlTypeConstant.BIGINT,isAutoIncrement = true)
    private Long id;

    @Column(name = "name",type = MySqlTypeConstant.CHAR)
    @NotBlank(message = "分类名称不能为空")
    private String name;

    //用户ID
    @Column(name = "userId",type = MySqlTypeConstant.BIGINT)
    private Long userId;

    private List<Blog> blogs = new ArrayList<Blog>();

}
