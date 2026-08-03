package com.moviemeter.moviemeter.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String genre;

}
