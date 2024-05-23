package com.zuitt.postApp.services;

import com.zuitt.postApp.models.Post;
import com.zuitt.postApp.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PostService {

    // Create Post
    void createPost (String stringToken, Post post);

    // Update Post
    ResponseEntity updatePost(Long id, String stringToken, Post post);

    // Delete Post
    ResponseEntity deletePost(Long id, String stringToken);

    ResponseEntity getAllPosts();

    ResponseEntity getMyPosts(String stringToken);
}
