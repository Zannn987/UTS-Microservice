package com.uts.microservice.movie_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uts.microservice.movie_service.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Additional query methods can be defined here if needed
}
