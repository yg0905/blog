package com.yg.blog.controller.admin;

import com.yg.blog.bean.User;
import com.yg.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes, Model model){
        User user=userService.checkUser(username,password);
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addAttribute("message","用户名或者密码错误");
            return "redirect:/admin";
        }
    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

//    @RequestMapping("logi")
//    public String logi(@RequestParam("username") final String username,@RequestParam("password")final String password){
//        //判断当前用户是被限制登录
//        Map<String, Object> map = userService.loginUserLock(username);
//        if ((boolean) map.get("flag")){
//            return "登录失败，因"+username+"用户超过限制登录次数，已经被禁止登录，还剩"+map.get("lockTime");
//        }else{
//            //没有被限制 执行登录功能
//            User logi = userService.logi(username, password);
//            //判断是否登录成功
//            if (logi!=null){
//                //清除对应的所有key
//                return "登录页面";
//            }else{
//                String s = userService.logiValdate(username);
//                return s;
//            }
//        }
//    }
}
