package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private UserEntity author;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "post")
    private Set<CommentEntity> comments;

    public PostEntity() {
        this.comments = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public PostEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PostEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public PostEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
