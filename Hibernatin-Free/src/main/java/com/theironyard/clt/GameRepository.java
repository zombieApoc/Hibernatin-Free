package com.theironyard.clt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Ultramar on 5/3/16.
 */
public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {
    Page<Game> findByGenre(Pageable page, String genre);
    Page<Game> findByReleaseYear(Pageable page, int year);
}
