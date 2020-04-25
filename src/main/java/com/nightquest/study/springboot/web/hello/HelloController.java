package com.nightquest.study.springboot.web.hello;

import com.nightquest.study.springboot.dto.hello.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto1(@RequestParam String name, @RequestParam int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/hello/dto/{name}/{amount}")
    public HelloResponseDto helloResponseDto2(@PathVariable String name, @PathVariable int amount) {
        return new HelloResponseDto(name, amount);
    }
}
