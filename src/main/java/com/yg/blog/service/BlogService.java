package com.yg.blog.service;

import com.yg.blog.bean.Blog;
import com.yg.blog.bean.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog saveBlog(Blog tag);

    Blog getBlog(Long id);

    Blog getBlogmkdown(Long id);

    Page<Blog> listBlogs(Pageable pageable, Long id);

    Map<String,List<Blog>> archiveBlog();

    Long blogcount();

    Page<Blog> listBlogs(Pageable p);

    List<Blog> listBlogs(Integer size);
    Page<Blog> listquery(String query,Pageable p);

    Page<Blog> listBlog(Pageable p, BlogQuery blogQuery);

    Blog updateBlog(Long id, Blog tag);

    void deleteBlog(Long id);
}
