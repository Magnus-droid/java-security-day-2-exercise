package com.booleanuk.library.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "videoGames")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year")
    private int year;

    @Column(name = "genre")
    private String genre;

    public VideoGame(String name, String publisher, int year, String genre) {
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.genre = genre;
    }

    public VideoGame(int id) {
        this.id = id;
    }
}
