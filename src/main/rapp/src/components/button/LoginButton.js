import React from 'react'
import Button from './Button'
import Login from './lbmenu/Login'
import './LoginButton.css'

export default class LoginButton extends React.Component {
    state = {
        // Перевести
        errorL: false,
        errorP: false,
        focusedL: false,
        focusedP: false,

        show: false,
        type: "login",
        dataL: {
            id: 1,
            text: "Войти",
            action: () => this.setState({show: true})
        },
        dataS: {
            id: 2,
            text: "Отмена",
            action: () => this.setState({show: false})
        },
        dataC: {
            id: 3,
            text: "Войти",
            action: () => {
                this.submit(this.state.type)
            }
        },
        email: '',
        password: ''
    }

    submit(type) {
        const url = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
        fetch(url + '/api/system', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: type,
                email: this.state.email,
                password: this.state.password
            })
        }).then(res => res.json())
            .then(
                (result) => {
                    this.setState({errorL: false})
                    this.setState({errorP: false})
                    console.log(result)
                    this.setState({show: false})
                },
                (error) => {
                    console.log(error)
                    this.setState({errorL: true})
                    this.setState({errorP: true})
                })

    }

    render() {
        return (
            <>
                <Button data={this.state.dataL}/>
                {this.state.show &&
                <div className={'modal'}>
                    <div className={'modal-body'}>
                        <div className={'title'}>
                            <h4 className={'login'}>Авторизация</h4>
                            <h4 className={'register'}>Регистрация</h4>
                            <h4 className={'restore'}>Восстановление</h4>
                        </div>
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
                        <div className={'footer'}>
                            <div className={'enter'}>
                                <Button data={this.state.dataC}/>
                            </div>
                            <div className={'cancel'}>
                                <Button data={this.state.dataS}/>
                            </div>
                        </div>
                    </div>
                </div>
                }
            </>
        )
    }
}