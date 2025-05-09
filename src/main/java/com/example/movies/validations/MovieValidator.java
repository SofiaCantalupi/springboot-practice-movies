package com.example.movies.validations;

import com.example.movies.exception.BusinessException;
import com.example.movies.exception.DuplicatedMovieException;
import com.example.movies.exception.InvalidYearException;
import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Optional;

@Component
public class MovieValidator {
    private static final int MIN_YEAR = 1895;
    private final MovieRepository repository;

    // Constructor
    public MovieValidator(MovieRepository repository) {
        this.repository = repository;
    }

    // Used to calculate maximunm release year = current year plus 2 years.
    private Integer getMaxYearAllowed(){
        return Year.now().getValue() + 2;
    }

    // Used to validate releaseYear being between MIN_YEAR and Max
    public void validateYear(Integer year){
        if(year != null && (year < MIN_YEAR || year > getMaxYearAllowed())){
            throw  new InvalidYearException("El anio debe ser mayor a " + MIN_YEAR + " y menor a " + getMaxYearAllowed());
        }
    }

    // Used to avoid duplicated movie by title and director
    public void validateTitleAndDirector(String title, String director){
        if(repository.existsByTitleAndDirector(title, director)){
            throw new DuplicatedMovieException("Ya existe una pelicula con ese titulo y director.");
        }
    }

    // Used to check if a movie exists has a different update ID but the same title and director
    public void validateTitleAndDirectorForUpdate(Long id, String title, String director) {
        Optional<Movie> existing = repository.findByTitleAndDirector(title, director);
        if (existing.isPresent() && !existing.get().getId().equals(id)) { // evaluate if the movie exists and has different id from the one being updated
            throw new DuplicatedMovieException("Ya existe una película con ese titulo y director.");
        }
    }

    // Used to validate if the genre is not 'documental' when year is minor to 1920
    public void validateGenre(String genre, Integer year){
        if( year < 1920 && genre.equalsIgnoreCase("documental")){
            throw new BusinessException("El genero no puede ser documental si el anio es menor a 1920.");
        }
    }

}
