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

        this.getPlayerCards();
        this.getDealerCards();
    }

    getPlayerCards(callback) {
        fetch('./getPlayerCards/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    playerCards: responseData
                });
            });
    }
    getDealerCards(callback) {
        fetch('./getDealerCards/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    dealerCards: responseData
                });
            });
    }

    hitMe() {
        fetch('./hitMe/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {
                this.state.playerCards.push(responseData);

                this.setState({
                    playerCards: this.state.playerCards
                });
            });
    }

    finish() {

        fetch('./finish/' + this.state.gameId)
            .then((response) => response.json())
            .then((responseData) => {

                this.setState({
                    dealerCards: responseData
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
            let cardClass = 'playing-card' + (card.faceDown ? ' face-down' : '');

            return (
                <div className={cardClass} key={card.code}>
                    <img src={imgSrc} />
                </div>
            );
        }) : [];


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
                <div className="blackjack-buttons">
                    <button onClick={this.hitMe()}>Hit me!</button>
                    <button onClick={this.finish()}>Finish!</button>
                </div>
            </div>
        );
    }
}

export default Table;