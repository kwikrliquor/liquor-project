package com.example.springblog.services;

import com.example.springblog.Post;
import com.example.springblog.PostRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postsdao;

//    private List<Post> posts;

    public PostService(PostRepository postsdao) {
        this.postsdao = postsdao;
    }

    public List<Post> getAll() {
        return (List<Post>) postsdao.findAll();
    }

    public Post findOne(int id) {
        return postsdao.findOne(id);
    }

    public Post create(Post post) {
        postsdao.save(post);
        return post;
    }

    public Post delete(Post post) {
        postsdao.delete(post);
        return post;
    }

    public Post edit(Post post) {
        postsdao.save(post);
        return post;
    }
}
