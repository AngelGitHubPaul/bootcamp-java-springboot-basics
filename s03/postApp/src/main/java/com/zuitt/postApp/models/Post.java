package com.zuitt.postApp.models;

// Package that contains annotations, classes, and methods for connecting your model to your table
import jakarta.persistence.*;

// @Entity annotation is used to mark a Java class as an entity, indicating that instances of this class will be persisted to a database table
@Entity

// @Table annotation designates the table name in the database
@Table(name="posts")

public class Post {

    // @Id annotation is used to mark a field or property of an entity class as the primary key
    @Id
    // @GeneratedValue annotation is used to specify how the primary key value should be generated for an entity automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column annotation specifies the mapped column for a persistent property or field
    @Column
    private String title;

    @Column
    private String content;

    // Relationship Model
    // @ManyToOne annotation indicates that many Post entities are associated with a single User entity.
    @ManyToOne
    // @JoinColumn annotation is used to specify the foreign key column for an association or collection of entities.
    // "user_id" - foreign key colums
    // nullable = false - ensures that user_id column cannot be null
    @JoinColumn(name = "user_id", nullable = false)

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // End of Relationship Model

    // constructors
    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getters and setters for title and content
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
