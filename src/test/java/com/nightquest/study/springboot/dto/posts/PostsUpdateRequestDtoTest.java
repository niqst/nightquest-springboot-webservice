package com.nightquest.study.springboot.dto.posts;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostsUpdateRequestDtoTest {
    final private String title = "test title";
    final private String content = "test content !";

    @Test
    public void PostsUpdateRequestDto() {
        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getContent()).isEqualTo(content);
    }
}
