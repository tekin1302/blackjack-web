package ro.blackjackweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blackjackweb.dto.FinishResponseDTO;
import ro.blackjackweb.dto.FirstRoundResponseDTO;
import ro.blackjackweb.dto.HitMeResponseDTO;
import ro.blackjackweb.service.CardService;

/**
 * This is the only and only controller for now.
 * It can do any action in the game
 */
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/getPlayersCards/{id}")
    public FirstRoundResponseDTO getPlayerCards(@PathVariable Long id) {
        return cardService.getFirstRoundCards(id);
    }

    @RequestMapping("/hitMe/{id}")
    public HitMeResponseDTO hitMe(@PathVariable Long id) {
        return cardService.hitMe(id);
    }

    @RequestMapping("/finish/{id}")
    public FinishResponseDTO finish(@PathVariable Long id) {
        return cardService.finish(id);
    }
}