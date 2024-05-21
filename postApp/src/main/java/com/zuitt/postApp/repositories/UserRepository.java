package com.zuitt.postApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Object> {

    // Query method for finding a user by their username
    User findByUsername(String username);
}
