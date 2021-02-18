package com.example.jwtdemo.service;

import com.example.jwtdemo.dao.UserDao;
import com.example.jwtdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User userDB=userDao.login(user);
        if(userDB!=null){
            return userDB;
        }
        throw new RuntimeException("认证失败!");
    }
}
