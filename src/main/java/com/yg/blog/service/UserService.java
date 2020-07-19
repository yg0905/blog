package com.yg.blog.service;

import com.yg.blog.bean.User;

public interface UserService {
    User checkUser(String username,String password);

    User logi(String username,String password);

//    String  logiValdate(String name);
//
//    Map<String,Object> loginUserLock(String name);

}
