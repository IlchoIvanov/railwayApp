package com.example.railwayapp.Model.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentAddDto {
    private Long pictureId;
    @NotNull(message = "Коментарът не може да е празен")
    @Size(min = 3, max = 400, message = "Коментарът трябва да бъде между 3 и 400 символа")
    private String content;
    private String author;

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public @NotNull(message = "Коментарът не може да е празен") @Size(min = 3, max = 400, message = "Коментарът трябва да бъде между 3 и 400 символа") String getContent() {
        return content;
    }

    public void setContent(@NotNull(message = "Коментарът не може да е празен") @Size(min = 3, max = 400, message = "Коментарът трябва да бъде между 3 и 400 символа") String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


