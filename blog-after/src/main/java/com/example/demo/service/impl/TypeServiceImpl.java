package com.example.demo.service.impl;

import com.example.demo.entity.Type;
import com.example.demo.exception.NotFoundException;
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
        return null;
    }


    public Type getTypeByName(String name){
        return typeMapper.getTypeByName(name);
    }

    public List<Type> getAllType(){
        return typeMapper.getAllType();
    }

    @Override
    public List<Type> listType(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public void updateType(Long id, Type type) {
        Type t = typeMapper.getType(id);
        if(t == null){
            throw new NotFoundException("不存在");
        }
        typeMapper.updateType(id,type);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }
}
