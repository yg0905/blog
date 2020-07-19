package com.yg.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//过滤器
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Loginlnterceptor()).addPathPatterns("/admin/**")//过滤那些请求
                .excludePathPatterns("/admin/login")//放行的请求
                .excludePathPatterns("/admin").excludePathPatterns("/admin/logout");
        super.addInterceptors(registry);
    }
}
