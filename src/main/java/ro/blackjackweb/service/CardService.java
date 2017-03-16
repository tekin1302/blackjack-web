package ro.blackjackweb.service;

import org.springframework.stereotype.Service;
import ro.blackjack.Card;
import ro.blackjack.Game;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService {

    private Map<Long, Game> gameMap;

    @PostConstruct
    public void init() {
        gameMap = new HashMap<>();
    }

    public List<Card> getShuffledCards(Long id) {

        Game game = getGameById(id);
        return game.getCardsPack();
    }

    private Game getGameById(Long id) {
        Game game;
        if ((game = gameMap.get(id)) == null) {
            game = new Game();
            game.init();
            gameMap.put(id, game);
        }
        return game;
    }

    public List<Card> getPlayerCards(Long id) {
        Game game = getGameById(id);
        return game.getPlayerCards();
    }

    public List<Card> getDealerCards(Long id) {
        Game game = getGameById(id);
        return game.getDealerCards();
    }
}
