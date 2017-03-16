import React from 'react';
import ReactDOM from 'react-dom';

import Card from './Card.jsx';
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

    getPlayersCards(callback) {
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
            let imgSrc = 'resources/img/cards/' + card.code + '.png';
            return (
                <div className="playing-card" key={card.code}>
                    <img src={imgSrc} />
                </div>
            );
        }) : [];

        let dealerCardImgs = this.state.dealerCards ? this.state.dealerCards.map((card) => {
            let imgSrc = 'resources/img/cards/' + card.code + '.png';
            let cardClass = 'playing-card' + (!this.state.finalMsg && card.faceDown ? ' face-down' : '');

            return (
                <div className={cardClass} key={card.code}>
                    <img src={imgSrc} />
                </div>
            );
        }) : [];

        let gameResults = this.state.finalMsg ? (
            <div>
                <h3>{this.state.finalMsg}</h3>
                <h4>Dealers score: {this.state.dealerScore}</h4>
                <h4>Your score: {this.state.playerScore}</h4>
            </div>
        ) : null;

        let actionButtons = this.state.finalMsg ? null : (
            <div className="blackjack-buttons">
                <button onClick={this.hitMe.bind(this)}>Hit me!</button>
                <button onClick={this.finishGame.bind(this)}>Finish!</button>
            </div>
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