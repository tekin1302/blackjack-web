package ro.blackjackweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blackjack.Card;
import ro.blackjackweb.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/getShuffledCards/{id}")
    public List<Card> getShuffledCards(@PathVariable Long id) {
        return cardService.getShuffledCards(id);
    }
    @RequestMapping("/getPlayerCards/{id}")
    public List<Card> getPlayerCards(@PathVariable Long id) {
        return cardService.getPlayerCards(id);
    }
    @RequestMapping("/getDealerCards/{id}")
    public List<Card> getDealerCards(@PathVariable Long id) {
        return cardService.getDealerCards(id);
    }
}