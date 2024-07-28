package com.example.railwayapp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    @Column(nullable = false)
    @Lob
    private String content;
    @ManyToOne(optional = false)
    private User author;
    @Column(nullable = false)
    private LocalDateTime time;
    @ManyToOne(optional = false)
    private Picture picture;

    public Comment() {
        this.time = LocalDateTime.now();
    }



    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
