package com.example.railwayapp.model.entity;

import com.example.railwayapp.model.entity.Enum.StationType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<Picture> pictures;



    public Station() {
     this.pictures = new ArrayList<>();
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



    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }


}
