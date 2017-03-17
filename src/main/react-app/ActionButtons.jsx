import React from 'react';
import ReactDOM from 'react-dom';

class ActionButtons extends React.Component {
    render () {
        return (
            <div className="blackjack-buttons">
                <button onClick={this.props.hitMe}>Hit me!</button>
                <button onClick={this.props.finishGame}>Finish!</button>
            </div>
        );
    }
}

export default ActionButtons;