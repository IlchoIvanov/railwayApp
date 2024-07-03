package com.example.railwayapp.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    private List<Station> stations;


    public RailwayLine() {
        this.stations = new ArrayList<>();
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





}
