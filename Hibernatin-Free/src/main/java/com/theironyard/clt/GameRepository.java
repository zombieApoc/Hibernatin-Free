package com.theironyard.clt;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ultramar on 5/3/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);
    List<Game> findByReleaseYear(int year);
}
