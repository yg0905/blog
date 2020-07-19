package com.yg.blog.service;

import com.yg.blog.bean.User;
import com.yg.blog.dao.UserRepository;
import com.yg.blog.util.MD5utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
//    @Autowired
//    RedisTemplate redisTemplate;
    @Override
    public User checkUser(String username, String password) {
        return repository.findByUsernameAndPassword(username, MD5utils.encoder(password));
    }

    @Override
    public User logi(String username, String password) {
        return null;
    }

//    @Override
//    public String logiValdate(String name) {
//        //记录登录次数
//        String key =User.getLoginxountLockKey(name);
//        int num=5;//错误的次数
//        if (!redisTemplate.hasKey(key)){//如果不存在
//            //注意redistemplate中在赋值时 不能直接赋值并设置失效期（会设置失效）
//            redisTemplate.opsForValue().set(key,"1");//先赋值
//            redisTemplate.expire(key,2,TimeUnit.MINUTES);//在设置失效期2分钟
//            return "登录失败，在2分钟内还允许输入错误"+(num-1)+"次";
//        }else{
//            //如果存在
//            //查询登录失败次数的key结果
//            long logincount=Long.parseLong((String) redisTemplate.opsForValue().get(key));
//            if (logincount<(num-1)){//如果登录失败次数<4 ,就就可以继续登录
//                redisTemplate.opsForValue().increment(key,1);//次数加一
//                long seconds=redisTemplate.getExpire(key,TimeUnit.SECONDS);//返回的是秒
//
//                return name + "登录失败，在" + seconds + "秒内允许输入错误" +(num - logincount - 1) + "次";
//            }else{//超过限制次数
//                //限制登录key存在，同时设置限制登录时间一个小时
//                redisTemplate.opsForValue().set(User.getLoginTimeLockKey(name),"1");
//                redisTemplate.expire(User.getLoginTimeLockKey(name),1,TimeUnit.HOURS);
//                return "因登录失败次数超过"+num+"次，限制一小时";
//            }
//        }
//    }
////判断是否被限制
//    @Override
//    public Map<String,Object> loginUserLock(String name) {
//        String key=User.getLoginTimeLockKey(name);
//        Map<String,Object> map=new HashMap<>();
//        if (redisTemplate.hasKey(key)) {
//            long lockTime=redisTemplate.getExpire(key, TimeUnit.MINUTES);//以分钟为单位进行返回
//            //如果存在
//            map.put("flag",true);
//            map.put("lockTime",lockTime);//还剩多长时间
//        }else{
//            map.put("flag",false);
//        }
//        return map;
//        }
  }
