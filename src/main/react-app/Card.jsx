import React from 'react';
import ReactDOM from 'react-dom';

class Card extends React.Component {
    render () {
        let card = this.props.data;
        let imgSrc = 'resources/img/cards/' + card.code + '.png';
        let cardClass = 'playing-card' + (card.faceDown ? ' face-down' : '');

        return (
            <div className={cardClass} >
                <img src={imgSrc} />
            </div>
        );
    }
}

export default Card;