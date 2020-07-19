//package com.yg.blog.util;
//
//import org.springframework.data.redis.core.RedisTemplate;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//
//public class  RedisUtil1{
//    private JedisPool jedisPool;
//    RedisTemplate redisTemplate;
//        //获得redispool实列 单列
//        public void initPool(String host,int port ,int database){
//            JedisPoolConfig poolConfig = new JedisPoolConfig();
//            poolConfig.setMaxTotal(200);//最大连接数
//            poolConfig.setMaxIdle(30);//最大空闲连接数
//            poolConfig.setBlockWhenExhausted(true);
//            poolConfig.setMaxWaitMillis(10*1000);//最大等待时间
//            poolConfig.setTestOnBorrow(true);//检查连接可用性，确保redis实列
//            jedisPool=new JedisPool(poolConfig,host,port,20*1000);
//        }
//        /**
//         * 从连接池中获取一个 Jedis 实例（连接）
//         * @return Jedis 实例
//         */
//        public Jedis getJedis(){
//            Jedis jedis = jedisPool.getResource();
//            return jedis;
//        }
//
//    }