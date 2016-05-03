package com.theironyard.clt;

import javax.persistence.*;

/**
 * Created by Ultramar on 5/3/16.
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    String platform;

    @Column(nullable = false)
    int releaseYear;

    public Game() {
    }

    public Game(String name, String genre, String platform, int releaseYear) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }
}
