package com.example.ott_finder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OttController {

    private final OttRepository ottRepository;

    public OttController(OttRepository ottRepository) {
        this.ottRepository = ottRepository;
    }

    @PostMapping("/api/otts")
    public String createOtt(@RequestBody OttCreateRequest request){
        Ott newOtt = new Ott(request.getName());
        ottRepository.save(newOtt);
        return "OTT 생성 성공";
    }

    @GetMapping("/api/otts")
    public List<Ott> getAllOtts(){
        return ottRepository.findAll();
    }
}
