package com.example.controller;

import com.example.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @Autowired
    private WelcomeService welcomeService;

    @GetMapping(value = "/")
    public String afficherMessageBienvenue(String nom, Model model) {
        model.addAttribute("Welcome",welcomeService.afficherMessage(nom,1));
        return "welcome-page";
    }
    @GetMapping(value = "/event")
    public String afficherMessageEvent(String participant, Model model) {
        model.addAttribute("WelcomeToEvent",welcomeService.afficherMessage(participant,2));
        return "event-page";
    }
}
