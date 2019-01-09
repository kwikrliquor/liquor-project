package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class PostController {
    Post post = new Post();
    @GetMapping("/posts")
    public String show(Model model) {
//        model.addAttribute("title", getTitle());
//        model.addAttribute("body", getBody());
//        model.addAttribute("title", allTitle);
//        model.addAttribute("body", allBody);
        ArrayList<String> allPosts = new ArrayList<>();
        allPosts.add(post.getTitle());
        allPosts.add(post.getBody());
        model.addAttribute("allposts", allPosts);
        return "posts/show";
    }

    @GetMapping("/posts/{id}")
    public String postid(@PathVariable int id, Model model) {
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create/{title}/{body}")
    public String postsCreate(@PathVariable String title, @PathVariable String body, Model model) {
        return "posts/show";
    }
}