package com.example.ott_finder;

public class ContentCreateRequest {
    private String title;
    private String category;
    private String imageUrl;
    private Integer releaseYear;
    private String director;
    private String description;

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
}
