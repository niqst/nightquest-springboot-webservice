package com.nightquest.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("Select p from Posts p order by p.id desc")  //query를 사용할 수 있음
    List<Posts> findAllDesc();
}
