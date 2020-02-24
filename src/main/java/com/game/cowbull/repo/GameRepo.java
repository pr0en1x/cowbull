package com.game.cowbull.repo;

import com.game.cowbull.entity.Game;
import com.game.cowbull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Long> {
    Iterable<Game> findAllByUser(User user);
}
