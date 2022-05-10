'use strict';

class LikeButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = { liked: false };
    }

    render() {
        if (this.state.liked) {
            return "nacisnieto przycisk z wartoscia \"commentid\" = " + this.props.commentID;
        }
        else {
            return React.createElement(
                'button',
                { onClick: () => this.setState({ liked: true }) }
                ,'ExampleButton');
        }
    }
}

document.querySelectorAll('.example_button_container')
    .forEach(domContainer => {
    const commentID = parseInt(domContainer.dataset.commentid, 10);
    const root = ReactDOM.createRoot(domContainer);
    root.render(React.createElement(LikeButton, { commentID: commentID }));
    }
);