package com.example.ott_finder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ott {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tmdbId;
    private String name;

    protected Ott() {}

    public Ott(String name){
        this.name = name;
    }

    public Ott(Integer tmdbId, String name){
        this.tmdbId = tmdbId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public Integer getTmdbId() {
        return tmdbId;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }
    public void setName(String name) {
        this.name = name;
    }
}
