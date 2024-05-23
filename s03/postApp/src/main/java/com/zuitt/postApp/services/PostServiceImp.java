package com.zuitt.postApp.services;

import com.zuitt.postApp.config.JwtToken;
import com.zuitt.postApp.models.Post;
import com.zuitt.postApp.models.User;
import com.zuitt.postApp.repositories.PostRepository;
import com.zuitt.postApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtToken jwtToken;

    public void createPost(String stringToken, Post post) {

        // retrieve the user object based on the provided token
        User author = userRepository.findByUsername(jwtToken.getUsernameFromToken(stringToken));

        // create a new Post object
        Post newPost = new Post();

        // Sets the title and content of the post
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        // sets the author of the post
        newPost.setUser(author);

        // Save the new post to the database
        postRepository.save(newPost);

    }

    // Update Post
    public ResponseEntity updatePost(Long id, String stringToken, Post post) {
        // Retrieve the post to be updated from the repository using the provided id
        Post postForUpdating = postRepository.findById(id).get();

        String postAuthor = postForUpdating.getUser().getUsername();

        // Extract username of the authenticated user from the provided token
        String authenticatedUser = jwtToken.getUsernameFromToken((stringToken));

        // Check if the authenticated user is the author of the post
        if(authenticatedUser.equals(postAuthor)) {
            // If the authenticated user is the author of the post, update the title and content of the post
            postForUpdating.setTitle(post.getTitle());
            postForUpdating.setContent(post.getContent());

            // save the updated post to the repository
            postRepository.save(postForUpdating);

            // Return the success message and HTTP status code 200 (OK)
            return  new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        } else {
            // If the authenticated user is NOT the author of the post, return an unauthorized message and HTTPS status 401 (UNAUTHORIZED)
            return new ResponseEntity<>("You are not authorized to edit this post", HttpStatus.UNAUTHORIZED);
        }
    }

    // Delete Post
    public ResponseEntity deletePost(Long id, String stringToken) {

        // Retrieve the post to be deleted from the repository using the provided id
        Post postForDeletion = postRepository.findById(id).get();

        // Get the username of the author from the post to be deleted
        String postAuthor = postForDeletion.getUser().getUsername();

        // Extract the username of the authenticated user from the provided token
        String authenticatedUser = jwtToken.getUsernameFromToken(stringToken);

        // Check if the authenticated user is the author of the post
        if(authenticatedUser.equals(postAuthor)) {
            // If the authenticated user is the author, delete the post from the repository
            postRepository.deleteById(id);

            return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("You are not authorized to delete this post", HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity getAllPosts() {

        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity getMyPosts(String stringToken) {

        String authenticatedUser = jwtToken.getUsernameFromToken(stringToken);

        long userId = userRepository.findByUsername(authenticatedUser).getId();

        return new ResponseEntity<>(postRepository.findAllById(userId), HttpStatus.OK);
    }
}
