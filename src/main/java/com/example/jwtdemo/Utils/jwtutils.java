package com.example.jwtdemo.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class jwtutils {

    private static final String SIGN="!@#$%";
    /**
     * 生成token header.payload.signature
     */
    public static String getToken(Map<String,String> map){
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.DATE,7); //7天过期

        //创建jwt builder
        JWTCreator.Builder builder=JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token=builder.withExpiresAt(instance.getTime())
               .sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    /**
     * 验证token
     */
    public static DecodedJWT  verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

//    /**
//     * 获取token信息方法
//     */
//    public static DecodedJWT getTokeninfo(String token){
//        DecodedJWT verify=JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
//        return verify;
//    }
}
