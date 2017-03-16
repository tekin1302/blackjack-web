import React from 'react';
import ReactDOM from 'react-dom';

import Table from './Table.jsx';

class Blackjack extends React.Component {
    render () {
        return (
            <div>
                <Table />
            </div>
        );
    }
}

ReactDOM.render(<Blackjack/>, document.getElementById('root'));