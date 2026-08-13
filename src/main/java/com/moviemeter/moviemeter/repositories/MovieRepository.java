package com.moviemeter.moviemeter.repositories;

import com.moviemeter.moviemeter.dto.MovieResponse;
import com.moviemeter.moviemeter.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("""
            SELECT new com.moviemeter.moviemeter.dto.MovieResponse(
                m.id,
                m.name,
                m.thumbnailUrl,
                m.language,
                m.description,
                m.genre,
                COALESCE(CAST(AVG(r.rating) AS Double), CAST(0.0 AS Double) )
            )
            FROM Movie m
            LEFT JOIN Review r
                ON r.movie = m
            WHERE
                (
                    :keyword IS NULL
                    OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(m.genre) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(m.language) LIKE LOWER(CONCAT('%', :keyword, '%'))
                )
            GROUP BY
                m.id,
                m.name,
                m.thumbnailUrl,
                m.language,
                m.description,
                m.genre
            """)
    List<MovieResponse> searchMovies(
        @Param("keyword") String keyword
    );
}
