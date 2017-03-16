package ro.blackjackweb.dto;

import ro.blackjack.Card;

import java.util.List;

/**
 * Created by tekin on 3/17/2017.
 */
public class FirstRoundResponseDTO extends BaseResponseDTO {
    private List<Card> playerCards;
    private List<Card> dealerCards;

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }
}
