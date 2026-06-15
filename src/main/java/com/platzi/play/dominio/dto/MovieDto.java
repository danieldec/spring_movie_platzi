package com.platzi.play.dominio.dto;

import com.platzi.play.dominio.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MovieDto(
        Long id
        , @NotNull @NotBlank String title
        , @Min(value = 1) Integer duration
        , Genre genre
        , LocalDate releaseDate
        , Double rating) {
}
