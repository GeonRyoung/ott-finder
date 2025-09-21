package com.example.ott_finder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TmdbMovieWatchProviders {

    @JsonProperty("link")
    private String link;

    @JsonProperty("flatrate")
    private List<TmdbOttProviderDto> flatrate;

    public String getLink() {
        return link;
    }

    public List<TmdbOttProviderDto> getFlatrate() {
        return flatrate;
    }
}
