package edu.nus.iss.cardProject.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import edu.nus.iss.cardProject.model.Card;
import edu.nus.iss.cardProject.model.Deck;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class DoCService {

    private static final String URL_DECK = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";

    private static final String URL_CARD = "https://deckofcardsapi.com/api/deck/";

    RestTemplate template = new RestTemplate();
    
    public Deck createDeck() throws IOException {

        Deck deck = new Deck();

        ResponseEntity<String> resp = template.getForEntity(URL_DECK, String.class);

        try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            
            JsonReader r = Json.createReader(is);

            JsonObject o = r.readObject();

            // System.out.println("Json object: " + o.toString());

            deck.setDeckId(o.getString("deck_id"));
            deck.setShuffled(o.getBoolean("shuffled"));
            deck.setRemainingCards(o.getInt("remaining"));

        }

        return deck;
    }

    public Deck drawCards(String deckId, Integer count) throws IOException {

        Deck deck = new Deck();
        List<Card> cards = new ArrayList<Card>();


        String url = UriComponentsBuilder.fromUriString(URL_CARD)
            .path(deckId + "/draw")
            .queryParam("count", count)
            .toUriString();

        // System.out.println("URL:  " + url);

        RequestEntity<Void> req = RequestEntity.get(url).build();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        try(InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            
            JsonReader r = Json.createReader(is);

            JsonObject o = r.readObject();

            System.out.println("Json object: "+ o.toString());

            deck.setDeckId(o.getString("deck_id"));
            deck.setRemainingCards(o.getInt("remaining"));

            JsonArray arr =  o.getJsonArray("cards");
              
            arr.stream()
            .map(v -> (JsonObject) v )
            .forEach(v -> {
                cards.add(new Card(v.getString("image")));
            });

        }

        deck.setCardsChoosen(cards);

        return deck;
    }
}
