import React from 'react'
import '../LoginButton.css'

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            focusedL: false,
            focusedP: false,
            focusedPP: false
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
                <div
                    className={'cool-input ' + (this.state.focusedP ? 'focused ' : ' ') + (this.props.errorPassword ? 'error' : '')}>
                    <span>Пароль</span>
                    <input
                        onClick={() => {
                            this.props.errorPasswordHandler()
                        }}
                        onBlur={() => this.setState({focusedP: false})}
                        onFocus={() => this.setState({focusedP: true})}
                        onChange={
                            event => this.props.passwordLabelHandler(event)
                        }
                        id={'password'}
                        type={'password'}/>
                </div>
                <div
                    className={'cool-input ' + (this.state.focusedPP ? 'focused ' : ' ') + (this.props.errorRepPassword ? 'error' : '')}>
                    <span>Повторите пароль</span>
                    <input
                        onClick={() => {
                            this.props.errorRepPasswordHandler()
                        }}
                        onBlur={() => this.setState({focusedPP: false})}
                        onFocus={() => this.setState({focusedPP: true})}
                        onChange={
                            event => this.props.repPasswordLabelHandler(event)
                        }
                        id={'rep-password'}
                        type={'password'}/>
                </div>
            </form>
        )
    }
}

export default Register