package com.example.railwayapp.model.entity;

import com.example.railwayapp.model.entity.Enum.Level;
import com.example.railwayapp.model.entity.Enum.UserRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "author")
    private List<Picture> userPictures;
    @OneToMany(mappedBy = "author")
    private List<Comment> userComments;
    @ManyToMany
    private List<Station> visitedStations;

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Picture> getUserPictures() {
        return userPictures;
    }

    public void setUserPictures(List<Picture> userPictures) {
        this.userPictures = userPictures;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    public List<Station> getVisitedStations() {
        return visitedStations;
    }

    public void setVisitedStations(List<Station> visitedStations) {
        this.visitedStations = visitedStations;
    }


    public User() {
        this.visitedStations = new ArrayList<>();
        this.level = Level.НОВ;
        this.userPictures = new ArrayList<>();
        this.userComments = new ArrayList<>();
        this.role = UserRole.USER;
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



    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
