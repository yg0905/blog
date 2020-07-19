package com.yg.blog.service;

import com.yg.blog.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagName(String name);

    List<Tag> listTag(Integer size);

    Page<Tag> listTag(Pageable p);

    List<Tag> tags();
    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
