package com.example.railwayapp.Model.Dto;

public class RailwayLineViewDto {
    private long id;
    private int number;
    private String randomPicturePath;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRandomPicturePath() {
        return randomPicturePath;
    }

    public void setRandomPicturePath(String randomPicturePath) {
        this.randomPicturePath = randomPicturePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
