package com.zuitt.postApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

// @Entity annotation is used to mark a Java class as an entity, indicating that instances of this class will be persisted to a database table
@Entity

// @Table annotation designates the table name in the database
@Table(name="users")
public class User {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    // @OneToMany indicates that there is a one-to-many relationship between the User and Post entities.
    // (mappedBy = "user") specifies that the Post entity (child table) owns the relationship.
    @OneToMany(mappedBy = "user")
    // Declaring "posts" field provided access to the collection of <Post> objects associated with the user
    @JsonIgnore
    private Set<Post> posts;

    // Getter method returns the set of post objects associated with the user
    public Set<Post> getPosts() {
        return posts;
    }
    // End of Relation Model

    // constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter for id
    public Long getId() {
        return id;
    }

    // getter and setter for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
