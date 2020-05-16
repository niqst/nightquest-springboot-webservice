package com.nightquest.study.springboot.web;

import com.nightquest.study.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class IndexController {
    final private PostsService postsService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.addObject("posts", postsService.getPostsAll());
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/posts/save")
    public String savePosts() {
        return "save-posts";
    }

    @GetMapping("/posts/update/{id}")
    public ModelAndView updatePosts(ModelAndView mav, @PathVariable Long id) {
        mav.addObject("post", postsService.getPosts(id));
        mav.setViewName("update-posts");
        return mav;
    }
}
