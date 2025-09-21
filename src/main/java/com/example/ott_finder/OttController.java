package com.example.ott_finder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OttController {

    private final OttRepository ottRepository;
    private final TmdbService tmdbService;

    public OttController(OttRepository ottRepository, TmdbService tmdbService) {
        this.ottRepository = ottRepository;
        this.tmdbService = tmdbService;
    }

    @PostMapping("/api/otts")
    public String createOtt(@RequestBody OttCreateRequest request){
        Ott newOtt = new Ott(request.getTmdbId(),request.getName());
        ottRepository.save(newOtt);
        return "OTT 생성 성공";
    }

    @GetMapping("/api/otts")
    public List<Ott> getAllOtts(){
        return ottRepository.findAll();
    }

    @PostMapping("/api/otts/fetch-tmdb")
    public String fetchOtts() {
        tmdbService.fetchAndSaveOttProviders();
        return "TMDB OTT 제공자 목록 저장 성공!";
    }
}
