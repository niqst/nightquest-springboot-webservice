package com.nightquest.study.springboot.web.posts;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import com.nightquest.study.springboot.dto.posts.PostsResponseDto;
import com.nightquest.study.springboot.dto.posts.PostsSaveRequestDto;
import com.nightquest.study.springboot.dto.posts.PostsUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PostsApiControllerTest {
    //TODO 실제 controller와 동기화되도록 설정에서 관리 필요
    final private String POSTS_URL = "/api/v1/posts";

    final private String title = "test title";
    final private String content = "test content";
    final private String author = "Kim";

    @LocalServerPort
    private int port;
    @Resource
    private TestRestTemplate restTemplate;
    @Resource
    private PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void get_posts() {
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        String url = "http://localhost:" + port + POSTS_URL + "/" + savedPosts.getId();

        ResponseEntity<PostsResponseDto> responseEntity = restTemplate.getForEntity(url, PostsResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        assertThat(responseEntity.getBody().getId()).isEqualTo(savedPosts.getId());
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(savedPosts.getTitle());
        assertThat(responseEntity.getBody().getContent()).isEqualTo(savedPosts.getContent());
        assertThat(responseEntity.getBody().getAuthor()).isEqualTo(savedPosts.getAuthor());
    }

    @Test
    public void save_posts() {
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url = "http://localhost:" + port + POSTS_URL;

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }

    @Test
    public void update_posts() {
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        Long updateId = savedPosts.getId();
        String updateTitle = "updateTitle";
        String updateContent = "updateContent";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:" + port + POSTS_URL + "/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Posts updatedPosts = postsRepository.findAll().get(0);
        assertThat(updatedPosts.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedPosts.getContent()).isEqualTo(updateContent);
    }

    @Test
    public void delete_posts() {
        //save new posts
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .build());

        String url = "http://localhost:" + port + POSTS_URL + "/" + savedPosts.getId();
        log.info("delete url : {}", url);

        //delete
        restTemplate.delete(url);

        List<Posts> postsList = postsRepository.findAll();

        assertThat(postsList.size()).isLessThanOrEqualTo(0);
    }
}
