package com.game.cowbull.repo;

import com.game.cowbull.entity.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepo extends CrudRepository<Rating, Long> {
    Rating findByUsername(String username);
}
