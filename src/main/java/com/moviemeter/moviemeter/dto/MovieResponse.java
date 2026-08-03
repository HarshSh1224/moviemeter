package com.moviemeter.moviemeter.dto;

public record MovieResponse(
        Long id,
        String name,
        String thumbnailUrl,
        String language,
        String description,
        String genre,
        Double avgRating
) {
}
