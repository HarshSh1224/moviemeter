package com.moviemeter.moviemeter.dto;

import jakarta.validation.constraints.NotBlank;

public record MovieRequest(
        @NotBlank
        String name,

        @NotBlank
        String thumbnailUrl,

        @NotBlank
        String language,

        @NotBlank
        String description,

        @NotBlank
        String genre
) {
}
