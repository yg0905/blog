package com.yg.blog.controller;

import com.yg.blog.bean.Tag;
import com.yg.blog.service.BlogService;
import com.yg.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable Long id, Model model) {
        List<Tag> tags=tagService.listTag(10000);
        if (id==-1){
            id=tags.get(0).getId();
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlogs(pageable,id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
