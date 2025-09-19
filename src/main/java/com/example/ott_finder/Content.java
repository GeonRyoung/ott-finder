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

    @ManyToMany
    @JoinTable(
            name = "content_ott",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "ott_id")
    )
    private Set<Ott> otts = new HashSet<>();

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
    public Set<Ott> getOtts(){
        return otts;
    }
}
