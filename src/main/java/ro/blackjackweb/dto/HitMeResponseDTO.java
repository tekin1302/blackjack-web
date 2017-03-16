package ro.blackjackweb.dto;

import ro.blackjack.Card;

/**
 * A response for when the user asks for another card
 */
public class HitMeResponseDTO extends BaseResponseDTO {
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
