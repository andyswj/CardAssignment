package edu.nus.iss.cardProject.model;

import java.util.List;

import jakarta.json.JsonObject;

public class Deck {
    
    private String deckId;
    private boolean shuffled;
    private Integer remainingCards;
    private List<Card> cardsChoosen;
    
    public Deck() {
    }

    public Deck(String deckId, boolean shuffled, Integer remainingCards) {
        this.deckId = deckId;
        this.shuffled = shuffled;
        this.remainingCards = remainingCards;
    }

    public String getDeckId() {
        return deckId;
    }

    public List<Card> getCardsChoosen() {
        return cardsChoosen;
    }

    public void setCardsChoosen(List<Card> cardsChoosen) {
        this.cardsChoosen = cardsChoosen;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public boolean getShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public Integer getRemainingCards() {
        return remainingCards;
    }

    public void setRemainingCards(Integer remainingCards) {
        this.remainingCards = remainingCards;
    }
    
}
