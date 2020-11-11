import React from 'react'
import '../LoginButton.css'

export default class Login extends React.Component {

    render() {
        return (
            <form className={'form'}>
                <div
                    className={'cool-input ' + (this.state.focusedL ? 'focused ' : ' ') + (this.state.errorL ? 'error' : '')}>
                    <span>Логин</span>
                    <input
                        onClick={() => this.setState({errorL: false})}
                        onBlur={() => this.setState({focusedL: false})}
                        onFocus={() => this.setState({focusedL: true})}
                        onChange={event => this.setState({email: event.target.value})}
                        id={'email'}/>
                </div>
                <div
                    className={'cool-input ' + (this.state.focusedP ? 'focused ' : ' ') + (this.state.errorP ? 'error' : '')}>
                    <span>Пароль</span>
                    <input
                        onClick={() => {
                            this.setState({errorP: false})
                        }}
                        onBlur={() => this.setState({focusedP: false})}
                        onFocus={() => this.setState({focusedP: true})}
                        onChange={event => this.setState({password: event.target.value})}
                        id={'password'}
                        type={'password'}/>
                </div>
            </form>
        )
    }
}
