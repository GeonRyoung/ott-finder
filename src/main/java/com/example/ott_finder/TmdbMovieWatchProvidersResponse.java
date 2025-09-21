package com.example.ott_finder;

import java.util.Map;

public class TmdbMovieWatchProvidersResponse {
    private Integer id;
    private Map<String, TmdbMovieWatchProviders> results;

    public Integer getId() {
        return id;
    }

    public Map<String, TmdbMovieWatchProviders> getResults() {
        return results;
    }
}
