package com.nightquest.study.springboot.web.posts;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    //TODO 실제 controller와 동기화되도록 설정에서 관리 필요
    final private String SAVE_POST_URL = "/api/v1/posts";

    final private String title = "test title";
    final private String content = "test content";
    final private String author = "Kim";

    @LocalServerPort
    private int port;
    @Resource
    private TestRestTemplate restTemplate;
    @Resource
    private PostsRepository postsRepository;

    @Test
    public void save_posts() {
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url = "http://localhost:" + port + SAVE_POST_URL;

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }
}
