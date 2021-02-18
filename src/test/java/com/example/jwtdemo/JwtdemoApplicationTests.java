package com.example.jwtdemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class JwtdemoApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String,Object> map=new HashMap<>();
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.SECOND,600);

       String token= JWT.create()
                //.withHeader(map) //header 可以不写，使用默认的header
                .withClaim("userId",21) //payload
                .withClaim("username","xiantao")
                .withExpiresAt(instance.getTime()) //指定过期时间
                .sign(Algorithm.HMAC256("!@#$%")); //签名

        System.out.println(token);
    }

    @Test
    public void test(){
        //创建验证对象
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("!@#$%")).build();
        DecodedJWT verify=jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTM2MTc0NTgsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhbnRhbyJ9.n-_gsATf3n5Tsc4XoUmql4E4FiMTUu7GtCLhj04wKGg");
//        System.out.println(verify.getClaim("userId").asString());
//        System.out.println(verify.getClaim("username").asString());

        System.out.println(verify.getClaims().get("userId").asInt());
        System.out.println(verify.getClaims().get("username").asString());
        System.out.println("过期时间"+verify.getExpiresAt());
    }

}
