package com.example.railwayapp.Model.Entity;

import com.example.railwayapp.Model.Entity.Enum.Level;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;
    @OneToMany(mappedBy = "author")
    private Set<Picture> userPictures;
    @OneToMany(mappedBy = "author")
    private Set<Comment> userComments;

    public User() {
        this.level = Level.НОВ;
        this.userPictures = new HashSet<>();
        this.userComments = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel() {
        this.level = level;
    }

    public Set<Picture> getUserPictures() {
        return userPictures;
    }

    public void setUserPictures(Set<Picture> userPictures) {
        this.userPictures = userPictures;
    }

    public Set<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments;
    }
}
