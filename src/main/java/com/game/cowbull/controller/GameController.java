package com.game.cowbull.controller;

import com.game.cowbull.entity.Game;
import com.game.cowbull.entity.User;
import com.game.cowbull.repo.GameRepo;
import com.game.cowbull.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/game")
    public String greeting(Map<String, Object> model) {
        return "game";
    }

    public String result, finalText;
    public int victories, tryings;
    public double midltryings;
    @PostMapping("/game")
    public String add(@RequestParam String text, @AuthenticationPrincipal Principal user, Map<String, Object> model) {
        if (result==null) {
            result=random();
        }
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
            User userFromDb = userRepo.findByUsername(user.getName());
            userFromDb.setRatio(midltryings);
            userRepo.save(userFromDb);
            finalText = text + " -- " + bulls + "Б" + cows + "К" + "\n" + " Вы победили!";
        } else {
            finalText = text + " -- " + bulls + "Б" + cows + "К";
        }
        Game game = new Game(finalText);
        gameRepo.save(game);


        Iterable<Game> games = gameRepo.findAll();

        model.put("games", games);

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
