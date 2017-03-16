package ro.blackjackweb.dto;

import ro.blackjack.Card;

import java.util.List;

/**
 * A response for when the game is finished
 */
public class FinishResponseDTO extends BaseResponseDTO {
    private List<Card> dealerCards;

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }
}
