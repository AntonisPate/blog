package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    /**
     * Creates a new post
     * @param post the post for creating
     * @return Post the created post
     */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Returns all posts
     * @return List<Post> all the posts
     */
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    /**
     * Returns single post based on id
     * @param id the id of the post
     * @return Post the searched post
     */
    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Deletes a post based on the id
     * @param id the id of the post
     */
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    /**
     * Updates a post based on the id and the body data
     * @param id the id of the post
     * @param postDetails the posted data
     * @return Post the updated post
     */
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).get();
        post.setTitle(postDetails.getTitle());
        post.setContext(postDetails.getContext());
        post.setCreatedAt(postDetails.getCreatedAt());
        post.setViews(postDetails.getViews());

        return postRepository.save(post);
    }

}
