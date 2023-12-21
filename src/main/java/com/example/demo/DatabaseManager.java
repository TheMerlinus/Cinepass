package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private Map<String, Movie> movies;

    public DatabaseManager() {
        movies = new HashMap<>();

        movies.put("1", new Movie("Avengers: Endgame", "Action", 150));
        movies.put("2", new Movie("The Shawshank Redemption", "Drama", 142));

    }

    public Movie getMovieById(String movieId) {
        return movies.get(movieId);
    }

}
