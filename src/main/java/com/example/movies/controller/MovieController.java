package com.example.movies.controller;

import com.example.movies.model.Movie;
import com.example.movies.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;

    // Constructor
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Movie> found = service.findById(id);

        if (found.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una pelicula con ID " + id);
        }

        return ResponseEntity.ok(found.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Movie movie){
        Movie saved = service.create(movie);
        // URI location = URI.create("/api/movies/" + saved.getId()); URI : direccion de un recurso recien creado. Es una buena practica REST (HTTP 1.1), permite que el cliente pueda hacer un GET al recurso recien creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Movie newMovie, @PathVariable Long id) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Movie> updated = service.update(id, newMovie);
        return ResponseEntity.ok(updated.get());
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAll(){
        List<Movie> movies = service.getAll();
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/year/{releaseYear}")
    public ResponseEntity<?> getByReleaseYear(@Valid @PathVariable Integer releaseYear){
        List<Movie> movies = service.findByReleaseYear(releaseYear);

        if(movies.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro una pelicula con el anio " + releaseYear);
        }

        return ResponseEntity.ok(movies);
    }

}
