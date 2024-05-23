package com.zuitt.postApp.controllers;

import com.zuitt.postApp.models.Post;
import com.zuitt.postApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.POST)

    public ResponseEntity<Object> createPost(@RequestHeader(value = "Authorization") String stringToken, @RequestBody Post post) {

        // Method for creating a Post
        postService.createPost(stringToken, post);

        // Returns a success message and HTTP status code 201 (CREATED)
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    // Update Post
    @RequestMapping(value = "/posts/{postid}", method = RequestMethod.PUT)

    // @PathVariable annotation is used to extract values from the URI and bind them to the method parameters
    public ResponseEntity<Object> updatePost(@PathVariable Long postid, @RequestHeader(value = "Authorization") String stringToken, @RequestBody Post post) {
        return  postService.updatePost(postid, stringToken, post);
    }

    // Delete Post
    @RequestMapping(value = "/posts/{postid}", method = RequestMethod.DELETE)

    public ResponseEntity<Object> deletePost(@PathVariable Long postid, @RequestHeader(value = "Authorization") String stringToken) {
        return postService.deletePost(postid, stringToken);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)

    public ResponseEntity<Object> getAllPost() {

        return postService.getAllPosts();
    }

    @RequestMapping(value = "/myPosts", method = RequestMethod.GET)

    public ResponseEntity<Object> getMyPosts(@RequestHeader(value = "Authorization") String stringToken) {

        return postService.getMyPosts(stringToken);
    }
}
