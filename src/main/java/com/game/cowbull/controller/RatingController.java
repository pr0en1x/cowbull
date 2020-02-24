package com.game.cowbull.controller;

import com.game.cowbull.entity.User;
import com.game.cowbull.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RatingController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/rating")
    public String rating(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();
        HashMap<String, Double> map = new HashMap<>();
        for (User user : users) {
            map.put(user.getUsername(), user.getRatio());
        }
        model.put("users", map);

        return "rating";
    }
}
