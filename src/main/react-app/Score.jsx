import React from 'react';
import ReactDOM from 'react-dom';

class Score extends React.Component {
    render () {
        return (
            <div>
                <h3>{this.props.finalMsg}</h3>
                <h4>Dealers score: {this.props.dealerScore}</h4>
                <h4>Your score: {this.props.playerScore}</h4>
            </div>
        );
    }
}

export default Score;