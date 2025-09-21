package com.example.ott_finder;

import java.util.List;
import java.util.Map;

public class TmdbWatchProviderResponse {

    private Map<String, List<TmdbOttProviderDto>> results;

    public Map<String, List<TmdbOttProviderDto>> getResults(){
        return results;
    }
}
