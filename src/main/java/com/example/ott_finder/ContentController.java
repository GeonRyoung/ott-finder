package com.example.ott_finder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    private final ContentRepository contentRepository;
    private final OttRepository ottRepository;
    private final TmdbService tmdbService;

    public ContentController(ContentRepository contentRepository, OttRepository ottRepository, TmdbService tmdbService) {
        this.contentRepository = contentRepository;
        this.ottRepository = ottRepository;
        this.tmdbService = tmdbService;
    }

    @PostMapping("/api/contents")
    public String createContent(@RequestBody ContentCreateRequest request) {
        Content newContent = new Content(request.getTitle(), request.getCategory(),
                request.getImageUrl(), request.getReleaseYear(),request.getDirector(),request.getDescription());
        contentRepository.save(newContent);
        return "저장 성공!";
    }

    @GetMapping("/api/contents")
    public List<Content> getAllContents(){
        return contentRepository.findAll();
    }

    @GetMapping("/api/contents/search")
    public List<Content> searchContents(
            @RequestParam(value = "title", required = false) String titleKeyword,
            @RequestParam(value = "category", required = false) String category
    ){
        if(titleKeyword != null && !titleKeyword.isEmpty() && category != null && !category.isEmpty()){
            return contentRepository.findByTitleContainingAndCategory(titleKeyword, category);
        } else if(titleKeyword != null && !titleKeyword.isEmpty()){
            return contentRepository.findByTitleContaining(titleKeyword);
        } else if(category != null && !category.isEmpty()){
            return contentRepository.findByCategory(category);
        } else{
            return contentRepository.findAll();
        }
    }

    @PostMapping("/api/contents/{contentId}/otts/{ottId}")
    public String addOttContent(@PathVariable Long contentId, @PathVariable Long ottId){
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘텐츠가 없습니다. id=" + contentId));

        Ott ott = ottRepository.findById(ottId)
                .orElseThrow(() -> new IllegalArgumentException("해당 OTT가 없습니다. ott=" + ottId));

        content.getOtts().add(ott);
        contentRepository.save(content);

        return "연결 성공";
    }

    @PostMapping("/api/contents/fetch-tmdb")
    public String fetchAndSaveMovies(){
        tmdbService.fetchAndSavePopularMovies();
        return "TMDB 인기 영화 데이터 저장 성공";
    }

    @PostMapping("/api/contents/search-tmdb")
    public String searchTmdb(@RequestParam String query) {
        tmdbService.searchAndSaveMovies(query);
        return "TMDB 검색 및 저장 성공!";
    }
}
