package edu.nus.iss.cardProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.nus.iss.cardProject.model.Deck;
import edu.nus.iss.cardProject.service.DoCService;

@Controller
@RequestMapping("/")
public class DoCController {

    @Autowired
    DoCService docSvc;

    @PostMapping("/deck")
    public String creatingDeck(Model model) {

        Deck deck = new Deck();

        try {
            deck = docSvc.createDeck();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        model.addAttribute("deck", deck);
        model.addAttribute("backImg", "/image/back.png");

        return "cardSelector";
    }

    @PostMapping(path="/deck/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String drawingCards(@ModelAttribute("count") int count, @PathVariable("id") String id, Model model) {

        Deck deck = new Deck();

        try {
            deck = docSvc.drawCards(id, count);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        model.addAttribute("deck", deck);
        model.addAttribute("backImg", "/image/back.png");

        return "cardSelector";
    }


    
}
