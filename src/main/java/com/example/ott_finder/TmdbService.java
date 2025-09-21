package com.example.ott_finder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TmdbService {

    private final RestTemplate restTemplate;
    private final TmdbConfig tmdbConfig;
    private final ContentRepository contentRepository;
    private OttRepository ottRepository;

    public TmdbService(RestTemplate restTemplate, TmdbConfig tmdbConfig, ContentRepository contentRepository, OttRepository ottRepository) {
        this.restTemplate = restTemplate;
        this.tmdbConfig = tmdbConfig;
        this.contentRepository = contentRepository;
        this.ottRepository = ottRepository;
    }

    public void fetchAndSavePopularMovies(){
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="
                + tmdbConfig.getApiKey()
                + "&language=ko-KR";

        TmdbPopularMoviesResponse response = restTemplate.getForObject(url, TmdbPopularMoviesResponse.class);

        if(response != null && response.getResults() != null){
            for(TmdbMovieDto movieDto : response.getResults()){
                Content content = new Content(
                        movieDto.getTitle(),
                        "영화",
                        "https://image.tmdb.org/t/p/w500" + movieDto.getPosterPath(),
                        movieDto.getReleaseDate() != null && !movieDto.getReleaseDate().isEmpty() ? Integer.parseInt(movieDto.getReleaseDate().substring(0, 4)) : 0,
                        null,
                        movieDto.getOverview()
                );

                contentRepository.save(content);
            }
        }
    }

    public void fetchAndSaveOttProviders(){
        String url = "https://api.themoviedb.org/3/watch/providers/movie?api_key="
                + tmdbConfig.getApiKey()
                + "@language=ko-KR&awatch_region=KR";

        TmdbWatchProviderResponse response = restTemplate.getForObject(url, TmdbWatchProviderResponse.class);

        if (response != null && response.getResults() != null) {
            List<TmdbOttProviderDto> providersInKr = response.getResults().get("KR");
            if (providersInKr != null) {
                for (TmdbOttProviderDto providerDto : providersInKr) {
                    Optional<Ott> existingOtt = ottRepository.findByTmdbId(providerDto.getProviderId());

                    if (existingOtt.isEmpty()) {
                        Ott newOtt = new Ott(providerDto.getProviderId(), providerDto.getProviderName());
                        ottRepository.save(newOtt);
                    }
                }
            }
        }

    }
}
