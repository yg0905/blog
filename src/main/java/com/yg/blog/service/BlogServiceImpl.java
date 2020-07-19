package com.yg.blog.service;

import com.yg.blog.NotFoundException;
import com.yg.blog.bean.Blog;
import com.yg.blog.bean.BlogQuery;
import com.yg.blog.bean.Type;
import com.yg.blog.dao.BlogRepository;
import com.yg.blog.util.MarkdownUtils;
import com.yg.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;

//    @Autowired
//    RedisUtil1 redisUtil1;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
             blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }
    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Blog getBlogmkdown(Long id) {
        Blog blog=blogRepository.getOne(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        String content=b.getContent();

        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
//        Jedis jedis=redisUtil1.getJedis();
//        if (jedis.get("views")==null){
//            jedis.set("views","1");
//        }else {
//            Integer views =Integer.parseInt(jedis.get("views"));
//            jedis.set("views", String.valueOf(views+1));
//        }
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> listBlogs(Pageable p) {
        return blogRepository.findAll(p);
    }

    @Override
    public List<Blog> listBlogs(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable= PageRequest.of(0,size,sort);
        return blogRepository.findAllTop(pageable);
    }

    @Override
    public Page<Blog> listquery( String query,Pageable p) {
        return blogRepository.findByQuery(query,p);
    }


    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable, Long id) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join=root.join("tags");
                return cb.equal(join.get("id"),id);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> year=blogRepository.findGroupby();
        Map<String, List<Blog>> map=new HashMap<>();
        for (String s : year) {
            map.put(s,blogRepository.findByYear(s));
        }
        return map;
    }

    @Override
    public Long blogcount() {

        return  blogRepository.count();
    }


    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog t=blogRepository.getOne(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }
        //过滤掉值为空的参数 只留不为空的
        BeanUtils.copyProperties(blog,t, MyBeanUtils.getNullPropertyNames(blog));
        t.setUpdateTime(new Date());
        return blogRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
