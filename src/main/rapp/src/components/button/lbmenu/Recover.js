import React from 'react'
import '../LoginButton.css'

class Recover extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            focusedL: false,
            focusedP: false,
        }
    }

    render() {
        return (
            <form className={'form'}>
                <div
                    className={'cool-input ' + (this.state.focusedL ? 'focused ' : ' ') + (this.props.errorEmail ? 'error' : '')}>
                    <span>Логин</span>
                    <input
                        onClick={() =>
                            this.props.errorEmailHandler()
                        }
                        onBlur={() => this.setState({focusedL: false})}
                        onFocus={() => this.setState({focusedL: true})}
                        onChange={
                            event => this.props.emailLabelHandler(event)
                        }
                        id={'email'}/>
                </div>
            </form>
        )
    }
}

export default Recover