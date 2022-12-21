package com.codegym.service;

import com.codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog> findByTitleContaining(Pageable pageable, String title);

    Page<Blog> findAll(Pageable pageable);

    void save(Blog blog);

    Blog findById(int id);

    void delete(Integer id);
}
