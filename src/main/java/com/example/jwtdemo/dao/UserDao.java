package com.example.jwtdemo.dao;

import com.example.jwtdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User login(User user);
}
