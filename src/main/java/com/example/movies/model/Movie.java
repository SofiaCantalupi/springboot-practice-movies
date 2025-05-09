package com.example.movies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulo es un campo obligatorio")
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Director es un campo obligatorio")
    @Column(nullable = false)
    private String director;

    @Min(value = 1895, message = "Debe ser posterior a 1895")
    @Column(nullable = false)
    private Integer releaseYear;

    private String genre;

    // Constructor
    public Movie() {
    }

    public Movie(String title, String director, Integer releaseYear, String genre, Long id) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.id = id;
    }

    // Getters & Setters

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public @NotNull @Size(min = 2, max = 100) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 2, max = 100) String title) {
        this.title = title;
    }

    public @NotNull String getDirector() {
        return director;
    }

    public void setDirector(@NotNull String director) {
        this.director = director;
    }

    public @Min(1895) Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(@Min(1895) Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
