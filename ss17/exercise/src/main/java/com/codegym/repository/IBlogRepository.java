package com.codegym.repository;

import com.codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    @Query(value = "select * from blog  where status = false ", nativeQuery = true)
    Page<Blog> findByTitleContaining(Pageable pageable, String title);

    @Query(value = "select * from blog where title like concat('%', :title, '%')", nativeQuery = true)
    List<Blog> findByName(@Param("title") String title);

//    @Query(value = "update blog set status = true where id = :id", nativeQuery = true)
//    void remove(@Param("id") Integer id);
}
