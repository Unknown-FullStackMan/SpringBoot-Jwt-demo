package com.example.jwtdemo.service;

import com.example.jwtdemo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(User user);
}
