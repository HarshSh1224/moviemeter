package com.moviemeter.moviemeter.contollers;

import com.moviemeter.moviemeter.dto.MovieRequest;
import com.moviemeter.moviemeter.dto.MovieResponse;
import com.moviemeter.moviemeter.services.MoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {
    private final MoviesService moviesService;

    @GetMapping("/")
    public ResponseEntity<List<MovieResponse>> getMovies(
        @RequestParam(required = false)
        String keyword
    ) {
        return ResponseEntity.ok(moviesService.getMovies(keyword));
    }

    @PostMapping("/")
    public ResponseEntity<MovieResponse> postMovie(@Valid @RequestBody MovieRequest request){
        return ResponseEntity.ok(moviesService.createMovie(request));
    }
}
