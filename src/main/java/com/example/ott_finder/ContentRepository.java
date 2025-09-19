package com.example.ott_finder;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByTitleContaining(String keyword);
}

