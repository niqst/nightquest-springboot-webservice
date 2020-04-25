package com.nightquest.study.springboot.service;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service Layer
 * 트랜잭션과 도메인간의 순서만 보장
 * 비지니스 로직의 처리는 도메인에서만 담당한다
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    final private PostsRepository postsRepository;

    @Transactional
    public Long savePosts(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }
}
