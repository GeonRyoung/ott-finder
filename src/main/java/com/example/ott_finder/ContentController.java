package com.example.ott_finder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    private final ContentRepository contentRepository;
    private final OttRepository ottRepository;

    public ContentController(ContentRepository contentRepository, OttRepository ottRepository) {
        this.contentRepository = contentRepository;
        this.ottRepository = ottRepository;
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
}
