package com.example.demo.service;


import com.example.demo.entity.User;

public interface UserService {

    User checkUser(String username, String password);

    User findUserById(Long id);
}
