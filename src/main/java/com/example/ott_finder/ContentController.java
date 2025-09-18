package com.example.ott_finder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController {

    private final ContentRepository contentRepository;


    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @PostMapping("/api/contents")
    public String createContent(@RequestBody ContentCreateRequest request) {
        Content newContent = new Content(request.getTitle(), request.getCategory());
        contentRepository.save(newContent);
        return "저장 성공!";
    }

    @GetMapping("/api/contents")
    public List<Content> getAllContents(){
        return contentRepository.findAll();
    }
}
