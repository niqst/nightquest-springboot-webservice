package com.nightquest.study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Resource
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void load_index() {
        String url = "http://localhost:" + port + "/";
        String body = restTemplate.getForObject(url, String.class);
        assertThat(body).contains("NightQuest-SpringBoot-WebService");
    }

    @Test
    public void load_save_posts() {
        String url = "http://localhost:" + port + "/posts/save";
        String body = restTemplate.getForObject(url, String.class);
        assertThat(body).contains("Reg Posts");
    }

}
