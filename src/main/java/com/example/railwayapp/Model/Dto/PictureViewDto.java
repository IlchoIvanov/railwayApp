package com.example.railwayapp.Model.Dto;

import java.util.ArrayList;
import java.util.List;

public class PictureViewDto {
    private Long id;
    private String path;
    private List<CommentViewDto> comments;

    public PictureViewDto() {
        comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<CommentViewDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentViewDto> comments) {
        this.comments = comments;
    }
}
