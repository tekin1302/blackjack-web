import React from 'react';
import ReactDOM from 'react-dom';

import Card from './Card.jsx';
import Score from './Score.jsx';
import ActionButtons from './ActionButtons.jsx';
import 'whatwg-fetch';

class Table extends React.Component {

    constructor() {
        super();
        this.state = {
            gameId: Date.now()
        };

        this.getPlayersCards();
    }

    checkScores(data) {
        if (data.gameOver) {

            var msg;
            if (data.winner === 'PLAYER') {
                msg = 'You win!'
            } else if (data.winner === 'DEALER') {
                msg = 'You lost! Game over!';
            } else {
                msg = 'It\'s a tie!';
            }
            this.setState({
                finalMsg: msg,
                playerScore: data.playerScore,
                dealerScore: data.dealerScore
            });
        }
    }

    getPlayersCards() {
        fetch('./getPlayersCards/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {
                this.checkScores(responseData);
                this.setState({
                    playerCards: responseData.playerCards,
                    dealerCards: responseData.dealerCards
                });
            });
    }

    hitMe() {
        fetch('./hitMe/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {
                this.checkScores(responseData);
                this.state.playerCards.push(responseData.card);

                this.setState({
                    playerCards: this.state.playerCards
                });
            });
    }

    finishGame() {

        fetch('./finish/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {

                this.checkScores(responseData);
                this.setState({
                    dealerCards: responseData.dealerCards
                });
            });
    }
    render () {

        let playerCardImgs = this.state.playerCards ? this.state.playerCards.map((card) => {
            return <Card data={card} key={card.code} />;
        }) : [];

        let dealerCardImgs = this.state.dealerCards ? this.state.dealerCards.map((card) => {
            card.faceDown = !this.state.finalMsg && card.faceDown;
            return <Card data={card} key={card.code} />;
        }) : [];

        let gameResults = this.state.finalMsg ?
            <Score finalMsg={this.state.finalMsg} dealerScore={this.state.dealerScore} playerScore={this.state.playerScore} />
            : null;

        let actionButtons = this.state.finalMsg ? null : (
            <ActionButtons hitMe={this.hitMe.bind(this)} finishGame={this.finishGame.bind(this)} />
        );
        return (
            <div>
                <div id="dealerSide">
                    <h2>Dealer cards:</h2>
                    {dealerCardImgs}
                </div>
                <div id="playerSide">
                    <h2>Your cards:</h2>
                    {playerCardImgs}
                </div>
                {gameResults}
                {actionButtons}
            </div>
        );
    }
}

export default Table;