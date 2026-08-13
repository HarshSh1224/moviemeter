package com.moviemeter.moviemeter.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
        @NotBlank
        String text,

        @NotNull
        Long movieId,

        @NotNull
        @Min(1)
        @Max(5)
        Integer rating

) {
}
