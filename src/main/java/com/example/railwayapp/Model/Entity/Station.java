package com.example.railwayapp.Model.Entity;

import com.example.railwayapp.Model.Entity.Enum.StationType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stations")
public class Station extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, name = "station_type")
    @Enumerated(EnumType.STRING)
    private StationType stationType;
    @Column(nullable = false, name = "tickets_sale")
    private boolean ticketsSale;
    private String description;
    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    private Set<Picture> pictures;


    @ManyToMany(mappedBy = "stations")
    private Set<RailwayLine> railwayLines;

    public Station() {
     this.pictures = new HashSet<>();
     this.railwayLines = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StationType getStationType() {
        return stationType;
    }

    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }

    public boolean isTicketsSale() {
        return ticketsSale;
    }

    public void setTicketsSale(boolean ticketsSale) {
        this.ticketsSale = ticketsSale;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<RailwayLine> getRailwayLines() {
        return railwayLines;
    }

    public void setRailwayLines(Set<RailwayLine> railwayLines) {
        this.railwayLines = railwayLines;
    }
}
