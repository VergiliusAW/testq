import React from 'react'
import Button from './Button'
import './LoginButton.css'

export default class LoginButton extends React.Component {
    state = {
        error: false,
        show: false,
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
                this.submit()
            }
        },
        email: '',
        password: ''
    }

    submit() {
        console.log(this.state.email)
        console.log(this.state.password)
        const url = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
        fetch(url + '/api/login', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: this.state.email,
                password: this.state.password
            })
        }).then(res => res.json())
            .then(
                (result) => {
                    this.setState({error: false})
                    console.log(result)
                    this.setState({show: false})
                },
                (error) => {
                    this.setState({error: true})
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
                            <h3>Авторизация</h3>
                        </div>
                        <form
                            className={'form'}>
                            <span className={(this.state.error ? 'error' : '')}>Логин</span>
                            <input onChange={event => this.setState({email: event.target.value})} id={'email'}
                                   type={'email'} required={'required'}/>
                            <span className={(this.state.error ? 'error' : '')}>Пароль</span>
                            <input onChange={event => this.setState({password: event.target.value})} id={'password'}
                                   type={'password'}/>
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