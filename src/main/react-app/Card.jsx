import React from 'react';
import ReactDOM from 'react-dom';

class Card extends React.Component {
    render () {
        let card = this.props.data;
        let imgSrc = 'resources/img/cards/' + (card.faceDown ? 'back' : card.code) + '.png';

        return (
            <div className="playing-card" >
                <img src={imgSrc} />
            </div>
        );
    }
}

export default Card;