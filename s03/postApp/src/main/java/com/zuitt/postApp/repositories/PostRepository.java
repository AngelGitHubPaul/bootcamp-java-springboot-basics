package com.zuitt.postApp.repositories;

import com.zuitt.postApp.models.Post;
import com.zuitt.postApp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository annotation is used to indicate that a class is a repository.
// Responsible for data access and manipulation
@Repository

// CrudRepository- Interface for generic CRUD operations on a repository for a specific type.
public interface PostRepository extends CrudRepository<Post, Object> {

    List<Post> findByUser(User user);
}
