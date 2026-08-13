package com.moviemeter.moviemeter.services;

import com.moviemeter.moviemeter.dto.ReviewRequest;
import com.moviemeter.moviemeter.exception.ResourceNotFoundException;
import com.moviemeter.moviemeter.models.Movie;
import com.moviemeter.moviemeter.models.Review;
import com.moviemeter.moviemeter.models.User;
import com.moviemeter.moviemeter.repositories.MovieRepository;
import com.moviemeter.moviemeter.repositories.ReviewRepository;
import com.moviemeter.moviemeter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public void createReview(ReviewRequest request, Authentication authentication) {
        var email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));

        Movie movie = movieRepository.findById(request.movieId()).orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + request.movieId()));

        Review review = reviewRepository
            .findByMovieAndUser(movie, user)
            .orElse(Review
                .builder()
                .user(user)
                .movie(movie)
                .build()
            );

        review.setText(request.text());
        review.setRating(request.rating());

        reviewRepository.save(review);
    }
}
