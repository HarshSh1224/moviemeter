package com.moviemeter.moviemeter.services;

import com.moviemeter.moviemeter.dto.MovieRequest;
import com.moviemeter.moviemeter.dto.MovieResponse;
import com.moviemeter.moviemeter.models.Movie;
import com.moviemeter.moviemeter.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesService {
    private final MovieRepository movieRepository;

    public List<MovieResponse> getMovies(String keyword){
        if (keyword == null ) {
            keyword = "";
        }
        return movieRepository.searchMovies(keyword);
    }

    public MovieResponse createMovie(MovieRequest movieRequest){
        var movie = Movie.builder()
            .name(movieRequest.name())
            .thumbnailUrl(movieRequest.thumbnailUrl())
            .language(movieRequest.language())
            .description(movieRequest.description())
            .genre(movieRequest.genre())
            .build();

        Movie savedMovie = movieRepository.save(movie);

        return new MovieResponse(
            savedMovie.getId(),
            savedMovie.getName(),
            savedMovie.getThumbnailUrl(),
            savedMovie.getLanguage(),
            savedMovie.getDescription(),
            savedMovie.getGenre(),
            0.0);
    }
}
