//package com.yg.blog.interceptor;
//
//import com.yg.blog.util.RedisUtil1;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RedisConfig {
//    //读取配置文件中的redis的ip地址
//    @Value("${spring.redis.host:disabled}")
//    private String host;
//    @Value("${spring.redis.port:0}")
//    private int port ;
//    @Value("${spring.redis.database:0}")
//    private int database;
//    @Bean
//    public RedisUtil1 getRedisUtil(){
//        if(host.equals("disabled")){
//            return null;
//        }
//        RedisUtil1 redisUtil=new RedisUtil1();
//        redisUtil.initPool(host,port,database);
//        return redisUtil;
//    }
//}
