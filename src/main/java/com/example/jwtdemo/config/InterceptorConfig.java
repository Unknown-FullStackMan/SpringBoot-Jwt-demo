package com.example.jwtdemo.config;


import com.example.jwtdemo.interceptors.jwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new jwtInterceptor())
                .addPathPatterns("/user/test")  //其他接口保护token验证
                .excludePathPatterns("/user/login"); //所有用户接口放行
    }
}
