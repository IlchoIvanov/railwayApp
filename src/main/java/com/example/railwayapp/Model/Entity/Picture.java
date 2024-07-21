package com.example.railwayapp.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Column(nullable = false)
    private String path;
   @ManyToOne
    private Station station;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private int likes;
    @Column(nullable = false, name = "cloudinary_id")
    private String CloudinaryId;
    @ManyToOne(optional = false)
    private User author;
    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
    private List<Comment> comments;


    public Picture() {
        this.comments = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public String getCloudinaryId() {
        return CloudinaryId;
    }

    public void setCloudinaryId(String cloudinaryId) {
        CloudinaryId = cloudinaryId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
