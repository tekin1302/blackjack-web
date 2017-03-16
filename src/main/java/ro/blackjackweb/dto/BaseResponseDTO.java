package ro.blackjackweb.dto;

import ro.blackjack.PlayerType;

/**
 * Contains the common information for all responses
 */
public class BaseResponseDTO {
    private boolean gameOver;
    private int playerScore;
    private int dealerScore;
    private PlayerType winner;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public PlayerType getWinner() {
        return winner;
    }

    public void setWinner(PlayerType winner) {
        this.winner = winner;
    }
}
