package com.uts.microservice.movie_service.controller;

import com.uts.microservice.movie_service.model.Movie;
import com.uts.microservice.movie_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Endpoint untuk mengambil semua movie
    @GetMapping
    public List<Movie> getAllMovies() {
        log.info("GET /api/movies accessed");
        return movieService.getAllMovies();
    }

    // Endpoint untuk mengambil movie berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        log.info("GET /api/movies/{} accessed", id);
        return movieService.getMovieById(id)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk membuat movie baru
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        log.info("POST /api/movies accessed with body: {}", movie);
        return movieService.createMovie(movie);
    }

    // Endpoint untuk mengupdate movie yang sudah ada
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        log.info("PUT /api/movies/{} accessed with body: {}", id, movieDetails);
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDetails);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            log.warn("PUT /api/movies/{} failed: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk menghapus movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable Long id) {
        log.info("DELETE /api/movies/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            movieService.deleteMovie(id);
            response.put("message", "Movie berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Movie tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/movies/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
