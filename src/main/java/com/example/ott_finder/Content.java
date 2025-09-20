package com.example.ott_finder;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;
    private String title;
    private String category;
    private String imageUrl;
    private Integer releaseYear;
    private String director;

    @Column(length = 1000)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "content_ott",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "ott_id")
    )
    private Set<Ott> otts = new HashSet<>();

    protected Content(){}

    public Content(String title, String category, String imageUrl,
                   Integer releaseYear, String director, String description) {
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.releaseYear = releaseYear;
        this.director = director;
        this.description = description;
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
    public String getImageUrl() {
        return imageUrl;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    public String getDirector() {
        return director;
    }
    public String getDescription() {
        return description;
    }

    public Set<Ott> getOtts(){
        return otts;
    }
}
