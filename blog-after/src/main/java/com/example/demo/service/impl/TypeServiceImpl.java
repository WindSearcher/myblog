package com.example.demo.service.impl;

import com.example.demo.entity.Type;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        typeMapper.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }


    public Type getTypeByName(String name){
        return typeMapper.getTypeByName(name);
    }

    public List<Type> getAllType(Long userId){
        return typeMapper.getAllType(userId);
    }

    /*
    * 根据分类创建的时间顺序来展示
    * */
    public List<Type> getTopType(Integer pageSize){
        return typeMapper.getTopType(pageSize);
    }

    @Override
    public List<Type> listType(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public void updateType(Type type) {
        typeMapper.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }
}
