package com.zuitt.postApp.models;

// Package that contains annotations, classes, and methods for connecting to your table
import jakarta.persistence.*;

// @Entity annotation is used to mark a Java class as an entity, indicating that instances of this class will be persisted to a database table
@Entity

// @Table annotation designates the table name in the database
@Table(name="posts")

public class Post {

    // @Id annotation is used to mark a field or property of an entity as the primary key
    @Id
    // @GeneratedValue annotation is used to specify how the primary key value should be generated for an entity automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column annotation specifies the mapped column for a persistent property or field
    @Column
    private String title;

    @Column
    private String content;

    // constructors
    public Post() {
    }

    public Post(String content, String title) {
        this.content = content;
        this.title = title;
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
