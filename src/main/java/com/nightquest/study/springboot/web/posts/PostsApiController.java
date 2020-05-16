package com.nightquest.study.springboot.web.posts;

import com.nightquest.study.springboot.dto.posts.PostsResponseDto;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import com.nightquest.study.springboot.dto.posts.PostsUpdateRequestDto;
import com.nightquest.study.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Web Layer
 * 외부의 요청과 응답만 처리
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    final private PostsService postsService;

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto getPosts(@PathVariable Long id) {
        return postsService.getPosts(id);
    }

    @PostMapping("/api/v1/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.savePosts(dto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody PostsUpdateRequestDto dto) {
        return postsService.updatePosts(id, dto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePosts(@PathVariable Long id) {
        return postsService.deletePosts(id);
    }

}
