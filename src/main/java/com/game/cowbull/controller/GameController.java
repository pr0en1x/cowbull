package com.game.cowbull.controller;

import com.game.cowbull.entity.Game;
import com.game.cowbull.entity.Rating;
import com.game.cowbull.entity.User;
import com.game.cowbull.repo.GameRepo;
import com.game.cowbull.repo.RatingRepo;
import com.game.cowbull.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/game")
    public String greeting(Map<String, Object> model) {
        return "game";
    }

    public String result, finalText;
    public double midltryings;
    @PostMapping("/game")
    public String add(@RequestParam String text, @AuthenticationPrincipal User user, Map<String, Object> model) {
        if (result==null) {
            result=random();
        }
        int victories = user.getVictories();
        int tryings = user.getTryings();
        tryings++;
        int bulls = 0;
        int cows = 0;
        for(int i= 0;i < 4;i++){
            if(text.charAt(i) == result.charAt(i)){
                bulls++;
            }else if(result.contains(text.charAt(i)+"")){
                cows++;
            }
        }

        if (bulls == 4) {
            result = null;
            victories++;
            midltryings = (double) tryings / victories;
            Rating rating = new Rating(user.getUsername(), midltryings);
            Rating ratingFromDb = ratingRepo.findByUsername(user.getUsername());
            if (ratingFromDb != null) {
                ratingFromDb.setRatio(midltryings);
                ratingRepo.save(ratingFromDb);
            } else {
                ratingRepo.save(rating);
            }

            finalText = text + " -- " + bulls + "Б" + cows + "К" + "  Вы победили!";
        } else {
            finalText = text + " -- " + bulls + "Б" + cows + "К";
        }

        user.setTryings(tryings);
        user.setVictories(victories);
        userRepo.save(user);

        Game game = new Game(finalText, user);
        gameRepo.save(game);

        Iterable<Game> games = gameRepo.findAllByUser(user);
        model.put("games", games);

        Iterable<Rating> ratings = ratingRepo.findAll();
        model.put("ratings", ratings);

        return "game";
    }

    public String random() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for(int i = 0; i < 4; i++){
            result += numbers.get(i).toString();
        }
        return result;
    }
}
