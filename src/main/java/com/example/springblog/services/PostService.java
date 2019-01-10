package com.example.springblog.services;

import com.example.springblog.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
        createPosts();
    }

    public List<Post> getAll() {
        return posts;
    }

    public Post create(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post edit(Post post) {
        Post pp = posts.get(post.getId() - 1);
        pp.setTitle(post.getTitle());
        pp.setBody(post.getBody());
        return pp;
    }

    public Post findOne(int id) {
        return posts.get(id - 1);
    }

    private void createPosts() {
        create(new Post("New Post 1", "alright 1"));
        create(new Post("New Post 2", "alright 2"));
    }
}
