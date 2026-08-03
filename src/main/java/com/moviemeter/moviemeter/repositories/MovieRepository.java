package com.moviemeter.moviemeter.repositories;

import com.moviemeter.moviemeter.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
