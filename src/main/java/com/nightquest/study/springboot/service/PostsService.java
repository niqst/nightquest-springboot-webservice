package com.nightquest.study.springboot.service;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import com.nightquest.study.springboot.dto.posts.PostsListResponseDto;
import com.nightquest.study.springboot.dto.posts.PostsResponseDto;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import com.nightquest.study.springboot.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Layer
 * 트랜잭션과 도메인간의 순서만 보장
 * 비지니스 로직의 처리는 도메인에서만 담당한다
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    final private PostsRepository postsRepository;

    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 읽기전용으로 사용하여 성능을 더 좋게
    public List<PostsListResponseDto> getPostsAll() {
        return postsRepository.findAll(new Sort(Sort.Direction.DESC, "id"))
                .stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public Long deletePosts(Long id) {
        postsRepository.deleteById(id);
        return id;
    }

    private Posts findById(Long id) {
        //TODO jpa getOne vs findById 찾아볼 것
        //return new postsRepository.getOne(id);
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found posts. id=" + id));
    }
}
