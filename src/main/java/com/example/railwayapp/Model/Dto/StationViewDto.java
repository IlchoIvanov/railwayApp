package com.example.railwayapp.Model.Dto;

import com.example.railwayapp.Model.Entity.Enum.StationType;

import java.util.ArrayList;
import java.util.List;

public class StationViewDto {
    private long id;
    private String name;
    private StationType stationType;
    private boolean ticketsSale;
    private String description;
    private List<PictureShortInfoDto> pictures;

    public StationViewDto() {
        this.pictures = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<PictureShortInfoDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureShortInfoDto> pictures) {
        this.pictures = pictures;
    }
}
