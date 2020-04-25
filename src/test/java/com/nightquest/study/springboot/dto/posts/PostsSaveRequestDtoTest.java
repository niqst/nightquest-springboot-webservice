package com.nightquest.study.springboot.dto.posts;

import com.nightquest.study.springboot.domain.posts.Posts;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PostsSaveRequestDtoTest {
    final private String title = "test title";
    final private String content = "test content !";
    final private String author = " Kim";

    @Test
    public void postsSaveRequestDto() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getContent()).isEqualTo(content);
        assertThat(dto.getAuthor()).isEqualTo(author);
    }

    @Test
    public void toEntity() {
        Posts posts = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build()
                .toEntity();

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);

    }
}
