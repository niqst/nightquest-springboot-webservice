package com.nightquest.study.springboot;

import com.nightquest.study.springboot.domain.posts.Posts;
import com.nightquest.study.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Resource
    private PostsRepository repository;

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void posts_create_and_read() {
        String title = "작성 테스트";
        String content = "작성 테스트 본문";
        String author = "작성자";

        repository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author).build());

        List<Posts> postsList = repository.findAll();
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }
}
