package com.nightquest.study.springboot.dto.hello;

import com.nightquest.study.springboot.dto.hello.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void helloResponseDto() {
        String name = "hello";
        int amount = 30;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
