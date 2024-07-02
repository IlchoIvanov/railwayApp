package com.example.railwayapp.Model.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "railway_lines")
public class RailwayLine extends BaseEntity {
    @Column(nullable = false)
    private int number;
    @Column(nullable = false)
    private String route;
    @Column(nullable = false)
    private Double length;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    private Set<Station> stations;
    @OneToMany(mappedBy = "railwayLine")
    private Set<Picture> pictures;

    public RailwayLine() {
        this.stations = new HashSet<>();
        this.pictures = new HashSet<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }


}
