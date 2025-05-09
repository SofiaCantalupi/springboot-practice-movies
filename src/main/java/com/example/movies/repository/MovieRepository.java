package com.example.movies.repository;

import com.example.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByReleaseYear(Integer releaseYear);
    boolean existsByTitleAndDirector(String title, String director);
    Optional<Movie> findByTitleAndDirector(String title, String director);

}
