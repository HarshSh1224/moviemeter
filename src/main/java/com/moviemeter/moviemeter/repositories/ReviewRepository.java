package com.moviemeter.moviemeter.repositories;

import com.moviemeter.moviemeter.models.Movie;
import com.moviemeter.moviemeter.models.Review;
import com.moviemeter.moviemeter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByMovieAndUser(Movie movie, User user);
}
