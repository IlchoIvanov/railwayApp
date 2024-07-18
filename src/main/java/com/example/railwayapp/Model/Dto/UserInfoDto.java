package com.example.railwayapp.Model.Dto;

import com.example.railwayapp.Model.Entity.Enum.Level;
import com.example.railwayapp.Model.Entity.Enum.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDto {
    private long id;
    private String username;
    private String email;
    private Level level;
    private UserRole role;
    private List<String> visitedStations;
    private int uploadedPictures;

    public UserInfoDto() {
        visitedStations = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<String> getVisitedStations() {
        return visitedStations;
    }

    public void setVisitedStations(List<String> visitedStations) {
        this.visitedStations = visitedStations;
    }

    public int getUploadedPictures() {
        return uploadedPictures;
    }

    public void setUploadedPictures(int uploadedPictures) {
        this.uploadedPictures = uploadedPictures;
    }
}
