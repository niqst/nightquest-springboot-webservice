package com.nightquest.study.springboot.web.posts;

import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import com.nightquest.study.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web Layer
 * 외부의 요청과 응답만 처리
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    final private PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.savePosts(dto);
    }

}
