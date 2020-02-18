package com.example.demo.mapper;

import com.example.demo.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Transactional
    @Insert("insert into type(name,userId) values(#{name},#{userId})")
    public void save(Type type);

    @Select("select * from type where id = #{id}")
    public Type getType(@Param("id") Long id);

    @Select("select * from type where userId = #{userId}")
    public List<Type> getAllType(@Param("userId") Long userId);

    //根据分类创建时间来展示
    @Select("select * from type order by id desc limit 0,#{pageSize}")
    public List<Type> getTopType(@Param("pageSize") Integer pageSize);

    @Select("select * from type where name = #{name}")
    public Type getTypeByName(@Param("name") String name);

    @Select("select * from type order by id limit #{page},#{pageSize}")
    public List<Type> listType(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    @Transactional
    @Update("update type set name = #{name} where id = #{id}")
    public void updateType(Type type);

    @Transactional
    @Delete("delete from type where id = #{id}")
    public void deleteType(@Param("id") Long id);

}
