package com.example.springblog;

import com.example.springblog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private List<Post> posts;
    private PostService postService;

    public PostController (PostService ps) {
        this.postService = ps;
    }

    @GetMapping("/posts")
    public String show(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postid(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findOne(id));
        model.addAttribute("id", id);
        return "/posts/show";
    }

    @GetMapping("/posts/create/{title}/{body}")
    @ResponseBody
    public String postsCreate(@PathVariable String title, @PathVariable String body, Model model) {
        return "kkkjkj";
    }
}