package com.moviemeter.moviemeter.contollers;

import com.moviemeter.moviemeter.dto.ReviewRequest;
import com.moviemeter.moviemeter.services.ReviewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;

    @PostMapping("/")
    public ResponseEntity<Void> postRating(@Valid @RequestBody ReviewRequest request, Authentication authentication) {
        reviewsService.createReview(request, authentication);
        return ResponseEntity.ok().build();
    }
}
