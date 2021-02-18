package com.example.jwtdemo.controller;



import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwtdemo.Utils.jwtutils;
import com.example.jwtdemo.entity.User;
import com.example.jwtdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Usercontroller {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Map<String,Object> login(User user){
        log.info("用户名:[{}]",user.getName());
        log.info("密码:[{}]",user.getPassword());
        Map<String,Object> map=new HashMap<>();
        try{
            User userDB=userService.login(user);
            Map<String,String> payload=new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("name",userDB.getName());
            //生成JWT的令牌
            String token=jwtutils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);//响应的token
        }catch ( Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @PostMapping("/user/test")
    public Map<String ,Object> test(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String token=request.getHeader("token");
        DecodedJWT verify=jwtutils.verify(token);
        log.info("用户id[{}]",verify.getClaim("id").asString());
        log.info("用户name[{}]",verify.getClaim("name").asString());
        map.put("state",true);
        map.put("msg","请求成功");
        return map;
    }
}
