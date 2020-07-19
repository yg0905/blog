package com.yg.blog.controller;

import com.alibaba.fastjson.JSON;
import com.yg.blog.service.BlogService;
import com.yg.blog.service.TagService;
import com.yg.blog.service.TypeService;
import com.yg.blog.util.HttpclientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @GetMapping("/index")
    public String show(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",blogService.listBlogs(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTag(10));
        model.addAttribute("recommendBlogs",blogService.listBlogs(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listquery("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getBlogmkdown(id));
        return "blog";

    }

    @GetMapping("/footer/newblog")
    public String about(Model model){
        model.addAttribute("newblogs",blogService.listBlogs(3));
        return "_fragments :: newblogList";
    }
    @RequestMapping("blogs")
    public String login(String code, HttpServletRequest request, Model model, HttpSession session){
        String u1="https://api.weibo.com/oauth2/access_token?client_id=3649047342&client_secret=598741b4105457df0105b4ca3dd6cde0&grant_type=authorization_code&redirect_uri=http://127.0.0.1:8080/blogs&code="+code+"";
        String u2= HttpclientUtil.doPost(u1,null);
        Map<String,String> map= JSON.parseObject(u2,Map.class);
        String access_token=map.get("access_token");
        String uid =map.get("uid");
        String s4="https://api.weibo.com/2/users/show.json?access_token="+access_token+"&uid="+uid;
        String user_json = HttpclientUtil.doGet(s4);
        Map<String,String> userMap= JSON.parseObject(user_json,Map.class);
        model.addAttribute("wb",userMap);
        session.setAttribute("name",userMap);
        return "qqindex";
    }
    @RequestMapping("/qqindex")
    public String qqshow(HttpSession session){
        System.out.println(session.getAttribute("name"));
        return "qqindex";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("name");
        return "redirect:/index";
    }
}
