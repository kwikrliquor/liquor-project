package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private PostService postService;

    public PostController (PostService ps) {
        this.postService = ps;
    }

    @GetMapping("/posts")
    public String show(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postid(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findOne(id));
        model.addAttribute("id", id);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        postService.create(post);
        return "redirect:/posts";
        // save the ad...
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findOne(id));
        return "posts/edit";
    }

    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findOne(id));
        postService.delete(postService.findOne(id));
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String edit(@ModelAttribute Post post) {
        postService.edit(post);
        return "redirect:/posts";
        // save the ad...
    }
}