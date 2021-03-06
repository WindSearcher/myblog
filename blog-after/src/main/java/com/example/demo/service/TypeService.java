package com.example.demo.service;

import com.example.demo.entity.Type;

import java.util.List;

public interface TypeService {

    public void save(Type type);

    public Type getType(Long id);

    public Type getTypeByName(String name);

    public List<Type> getAllType(Long userId);

    //取前几条
    public List<Type> getTopType(Integer pageSize);

    public List<Type> listType(Integer page, Integer pageSize);

    public void updateType(Type type);

    public void deleteType(Long id);
}
