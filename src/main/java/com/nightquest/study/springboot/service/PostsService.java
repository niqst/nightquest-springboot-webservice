package com.nightquest.study.springboot.service;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import com.nightquest.study.springboot.dto.posts.PostsResponseDto;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import com.nightquest.study.springboot.dto.posts.PostsUpdateRequestDto;
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
    public PostsResponseDto getPosts(Long id) {
        return new PostsResponseDto(findById(id));
    }

    @Transactional
    public Long savePosts(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long updatePosts(Long id, PostsUpdateRequestDto dto) {
        Posts posts = findById(id);

        //jpa 영속성에 의해 트랜잭션 내 로드된 객체는 트랜잭션이 끝날때 update query가 반영된다
        posts.update(dto);
        return posts.getId();
    }

    private Posts findById(Long id) {
        //TODO jpa getOne vs findById 찾아볼 것
        //return new postsRepository.getOne(id);
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found posts. id=" + id));
    }
}
