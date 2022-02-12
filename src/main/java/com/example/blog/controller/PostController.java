package com.example.blog.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value="/posts", method=RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        post.setViews(0L);
        Date date = new Date();
        post.setCreatedAt(date);
        return postService.createPost(post);
    }

    @RequestMapping(value="/posts", method=RequestMethod.GET)
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @RequestMapping(value="/posts/{id}", method=RequestMethod.GET)
    public Optional<Post> getPost(@PathVariable(value = "id") Long id) {
        Post post = postService.getPost(id).orElseThrow();
        post.setViews(post.getViews() + 1);
        postService.updatePost(id, post);
        return postService.getPost(id);
    }

    @RequestMapping(value="/posts/{id}", method=RequestMethod.PUT)
    public Post updatePost(@PathVariable(value = "id") Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @RequestMapping(value="/posts/{id}", method=RequestMethod.DELETE)
    public void deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePost(id);
    }
}
