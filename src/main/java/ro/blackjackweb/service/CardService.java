package ro.blackjackweb.service;

import org.springframework.stereotype.Service;
import ro.blackjack.Card;
import ro.blackjack.Game;
import ro.blackjackweb.dto.FinishResponseDTO;
import ro.blackjackweb.dto.FirstRoundResponseDTO;
import ro.blackjackweb.dto.HitMeResponseDTO;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * A service class that contains all the necessary operations for a blackjack game
 */
@Service
public class CardService {

    // since CardService is a singleton, I can store a map with games
    private Map<Long, Game> gameMap;

    @PostConstruct
    public void init() {
        gameMap = new HashMap<>();
    }

    /**
     * Return a game by the given id. If the game does not exist, it will be created
     * @param id - A unique value that defines a game
     * @return A game by id
     */
    private Game getGameById(Long id) {
        Game game;
        if ((game = gameMap.get(id)) == null) {
            game = new Game();
            game.init();
            gameMap.put(id, game);
        }
        return game;
    }

    /**
     * Get another card from the deck
     * @param id - A unique value that defines a game
     * @return
     */
    public HitMeResponseDTO hitMe(Long id) {
        Game game = getGameById(id);
        game.hitMe();

        HitMeResponseDTO hitMeResponseDTO = new HitMeResponseDTO();

        Card card = game.getPlayerCards().get(game.getPlayerCards().size() - 1);
        hitMeResponseDTO.setCard(card);
        hitMeResponseDTO.setGameOver(game.isGameOver(false));
        hitMeResponseDTO.setPlayerScore(game.getPlayerScore());
        hitMeResponseDTO.setDealerScore(game.getDealerScore());
        hitMeResponseDTO.setWinner(game.getWinner());

        return hitMeResponseDTO;
    }

    /**
     * Get the cards for the first round (2 cards each player)
     * @param id - A unique value that defines a game
     * @return
     */
    public FirstRoundResponseDTO getFirstRoundCards(Long id) {
        Game game = getGameById(id);

        FirstRoundResponseDTO firstRound = new FirstRoundResponseDTO();
        firstRound.setPlayerCards(game.getPlayerCards());
        firstRound.setDealerCards(game.getDealerCards());
        firstRound.setGameOver(game.isGameOver(true));
        firstRound.setPlayerScore(game.getPlayerScore());
        firstRound.setDealerScore(game.getDealerScore());
        firstRound.setWinner(game.getWinner());

        return firstRound;
    }

    /**
     * Finish a game by allowing the dealer to do his moves
     * @param id - A unique value that defines a game
     * @return
     */
    public FinishResponseDTO finish(Long id) {
        Game game = getGameById(id);
        game.finishUserTurn();
        game.makeDealerMoves(false);

        FinishResponseDTO finishResponse = new FinishResponseDTO();
        finishResponse.setDealerCards(game.getDealerCards());
        finishResponse.setGameOver(true);
        finishResponse.setPlayerScore(game.getPlayerScore());
        finishResponse.setDealerScore(game.getDealerScore());
        finishResponse.setWinner(game.getWinner());

        return finishResponse;
    }

}
