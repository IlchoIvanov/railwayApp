package com.example.railwayapp.Model.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentAddDto {
    private Long pictureId;
    @NotNull(message = "{comment_not_null}")
    @Size(min = 3, max = 400, message = "{comment_size}")
    private String content;
    private String author;

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public @NotNull(message = "{comment_not_null}") @Size(min = 3, max = 400, message = "{comment_size}") String getContent() {
        return content;
    }

    public void setContent(@NotNull(message = "{comment_not_null}") @Size(min = 3, max = 400, message = "{comment_size}") String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


