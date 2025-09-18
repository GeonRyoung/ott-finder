package com.example.ott_finder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;
    private String title;
    private String category;

    protected Content(){}

    public Content(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
