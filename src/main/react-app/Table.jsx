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

    render () {

        let playerCardImgs = this.state.playerCards ? this.state.playerCards.map((card) => {
            let imgSrc = 'resources/img/cards/' + card.code + '.png';
            return <img src={imgSrc} className="playing-card" key={card.code} />;
        }) : [];


        return (
            <div>
                <div id="dealerSide">

                </div>
                <div id="playerSide">
                    {playerCardImgs}
                </div>
            </div>
        );
    }
}

export default Table;