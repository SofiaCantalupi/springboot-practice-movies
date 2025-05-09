package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import com.example.movies.validations.MovieValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final MovieValidator validator;

    // Constructor
    public MovieService(MovieRepository repository, MovieValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    // Methods
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public Movie create(Movie movie) {
        validator.validateGenre(movie.getGenre(), movie.getReleaseYear());
        validator.validateYear(movie.getReleaseYear());
        validator.validateTitleAndDirector(movie.getTitle(), movie.getDirector());
        return repository.save(movie);
    }

    public Optional<Movie> update(Long id, Movie newData) {
        validator.validateYear(newData.getReleaseYear());

        validator.validateTitleAndDirectorForUpdate(id, newData.getTitle(), newData.getDirector());

        return this.findById(id)
                .map(existing -> {
                    existing.setTitle(newData.getTitle());
                    existing.setDirector(newData.getDirector());
                    existing.setGenre(newData.getGenre());
                    existing.setReleaseYear(newData.getReleaseYear());
                    return repository.save(existing);
                });
    }

    public List<Movie> getAll() {
        return repository.findAll();
    }

    public List<Movie> findByReleaseYear(Integer yearRelease) {
        validator.validateYear(yearRelease);
        return repository.findByReleaseYear(yearRelease);
    }


}
