package com.example.railwayapp.Model.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RailwayLineAddDto {
    @NotNull(message = "Въведете валидни данни")
    @Positive(message = "Номерът на жп линията трябва да е положително число")
    private int number;
    @NotNull(message = "Въведете валидни данни")
    @Size(min=5, message = "Минимум 5 символа")
    private String route;
    @NotNull(message = "Въведете валидни данни")
    @Positive(message = "Дължината на жп линията трябва да е положително число")
    private Double length;
    @NotNull(message = "Въведете валидни данни")
    @Size(min=5, message = "Минимум 5 символа")
    private String description;

    public int getNumber() {
        return number;
    }

    public void setNumber(@NotNull(message = "Въведете валидни данни") @Positive(message = "Номерът на жп линията трябва да е положително число") int number) {
        this.number = number;
    }

    public @NotNull(message = "Въведете валидни данни") @Size(min = 5, message = "Минимум 5 символа") String getRoute() {
        return route;
    }

    public void setRoute(@NotNull(message = "Въведете валидни данни") @Size(min = 5, message = "Минимум 5 символа") String route) {
        this.route = route;
    }

    public @NotNull(message = "Въведете валидни данни") @Positive(message = "Дължината на жп линията трябва да е положително число") Double getLength() {
        return length;
    }

    public void setLength(@NotNull(message = "Въведете валидни данни") @Positive(message = "Дължината на жп линията трябва да е положително число") Double length) {
        this.length = length;
    }

    public @NotNull(message = "Въведете валидни данни") @Size(min = 5, message = "Минимум 5 символа") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "Въведете валидни данни") @Size(min = 5, message = "Минимум 5 символа") String description) {
        this.description = description;
    }
}
