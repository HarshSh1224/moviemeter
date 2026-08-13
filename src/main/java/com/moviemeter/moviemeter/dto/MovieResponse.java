package com.moviemeter.moviemeter.dto;

import com.moviemeter.moviemeter.models.Movie;

public record MovieResponse(
        Long id,
        String name,
        String thumbnailUrl,
        String language,
        String description,
        String genre,
        Double avgRating
) {
    public MovieResponse(Movie movie) {
        this(
            movie.getId(),
            movie.getName(),
            movie.getThumbnailUrl(),
            movie.getLanguage(),
            movie.getDescription(),
            movie.getGenre(),
            0.00
        );
    }

    public MovieResponse(Movie movie, Double avgRating) {
        this(
            movie.getId(),
            movie.getName(),
            movie.getThumbnailUrl(),
            movie.getLanguage(),
            movie.getDescription(),
            movie.getGenre(),
            avgRating
        );
    }
}
