import React from 'react'
import Button from './Button'
import Login from './lbmenu/Login'
import Register from './lbmenu/Register'
import Recover from './lbmenu/Recover'
import './LoginButton.css'

export default class LoginButton extends React.Component {
    state = {
        errorRepPassword: false,
        errorPassword: false,
        errorEmail: false,
        show: false,
        type: 'login',
        enterButtonText: "Войти",
        dataL: {
            action: () => this.setState({show: true})
        },
        dataCancel: {
            action: () => {
                this.resetError()
                this.setState({show: false})
            }
        },
        dataEnter: {
            action: () => {
                this.submit(this.state.type)
            }
        },
        dataLogin: {
            action: () => {
                this.loginTab()
            }
        },
        dataRegister: {
            action: () => {
                this.registerTab()
            }
        },
        dataRecover: {
            action: () => {
                this.recoverTab()
            }
        },
        email: '',
        password: '',
        repPassword: ''
    }

    validateEmail(email) {
        const regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
        return regex.test(email)
    }

    loginTab() {
        this.resetError()
        this.setState({type: "login"})
        this.setState({enterButtonText: "Войти"})
    }

    registerTab() {
        this.resetError()
        this.setState({type: "register"})
        this.setState({enterButtonText: "Зарегистрироваться"})
    }

    recoverTab() {
        this.resetError()
        this.setState({type: "recover"})
        this.setState({enterButtonText: "Восстановить"})
    }

    emailLabelHandler = (event) => {
        this.setState(
            {email: event.target.value}
        )
    }

    passwordLabelHandler = (event) => {
        this.setState({password: event.target.value})
    }

    repPasswordLabelHandler = (event) => {
        this.setState({repPassword: event.target.value})
    }

    errorEmailHandler = () => {
        this.setState({errorEmail: false})
    }

    errorPasswordHandler = () => {
        this.setState({errorPassword: false})
    }

    errorRepPasswordHandler = () => {
        this.setState({errorRepPassword: false})
    }

    resetError() {
        this.setState({errorEmail: false})
        this.setState({errorPassword: false})
        this.setState({errorRepPassword: false})
    }

    setAllError() {
        this.setState({errorEmail: true})
        this.setState({errorPassword: true})
        this.setState({errorRepPassword: true})
    }

    setEmailError() {
        this.setState({errorEmail: true})
    }

    setPasswordError() {
        this.setState({errorPassword: true})
    }

    setRepPasswordError() {
        this.setState({errorRepPassword: true})
    }

    setPasError() {
        this.setState({errorPassword: true})
        this.setState({errorRepPassword: true})
    }

    submit(type) {
        this.resetError()
        if (this.validateEmail(this.state.email)) {
            var data
            // eslint-disable-next-line default-case
            var p = this.state.errorPassword
            var pp = this.state.errorRepPassword

            switch (type) {
                case 'login':
                    if (!(this.state.password === ''))
                        data = {
                            type: type,
                            email: this.state.email,
                            password: this.state.password
                        }
                    else {
                        p = true
                        this.setPasswordError()
                    }
                    break
                case 'register':
                    if (!(this.state.password === ''))
                        if (!(this.state.repPassword === ''))
                            if (this.state.password === this.state.repPassword)
                                data = {
                                    type: type,
                                    email: this.state.email,
                                    password: this.state.password,
                                    repPassword: this.state.repPassword
                                }
                            else {
                                p = true
                                pp = true
                                this.setPasError()
                            }
                        else {
                            pp = true
                            this.setRepPasswordError()
                        }
                    else {
                        p = true
                        this.setPasswordError()
                    }
                    break
                case 'recover':
                    data = {
                        type: type,
                        email: this.state.email
                    }
                    break
            }
            if (!this.state.errorEmail && !p && !pp) {
                const url = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '')
                fetch(url + '/api/system', {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                }).then(res => res.json())
                    .then(
                        (result) => {
                            if (result.res === 'sl') {
                                this.resetError()
                                this.setState({show: false})
                            } else if (result.res === 'srg') {
                                this.resetError()
                                this.setState({show: false})
                            } else if (result.res === 'src') {
                                this.resetError()
                                this.setState({show: false})
                            } else {
                                this.setAllError()
                            }
                        },
                        (error) => {
                            console.log(error)
                            this.setAllError()
                        })
            }
        } else {
            this.setEmailError()
        }
    }

    render() {
        return (
            <>
                <Button data={this.state.dataL}>Войти</Button>
                {this.state.show &&
                <div className={'modal'}>
                    <div className={'modal-body'}>
                        <div className={'title'}>
                            <h4 className={'login' + (this.state.type === 'login' ? ' active' : '')}>
                                <Button data={this.state.dataLogin}>
                                    Авторизация
                                </Button>
                            </h4>
                            <h4 className={'register' + (this.state.type === 'register' ? ' active' : '')}>
                                <Button data={this.state.dataRegister}>
                                    Регистрация
                                </Button>
                            </h4>
                            <h4 className={'recover' + (this.state.type === 'recover' ? ' active' : '')}>
                                <Button data={this.state.dataRecover}>
                                    Восстановление
                                </Button>
                            </h4>
                        </div>
                        {this.state.type === 'login' && (
                            <Login
                                emailLabelHandler={this.emailLabelHandler}
                                passwordLabelHandler={this.passwordLabelHandler}
                                errorEmailHandler={this.errorEmailHandler}
                                errorPasswordHandler={this.errorPasswordHandler}
                                errorEmail={this.state.errorEmail}
                                errorPassword={this.state.errorPassword}
                            />
                        )}
                        {this.state.type === 'register' && (
                            <Register
                                emailLabelHandler={this.emailLabelHandler}
                                passwordLabelHandler={this.passwordLabelHandler}
                                repPasswordLabelHandler={this.repPasswordLabelHandler}
                                errorEmailHandler={this.errorEmailHandler}
                                errorPasswordHandler={this.errorPasswordHandler}
                                errorRepPasswordHandler={this.errorRepPasswordHandler}
                                errorEmail={this.state.errorEmail}
                                errorPassword={this.state.errorPassword}
                                errorRepPassword={this.state.errorRepPassword}
                            />
                        )}
                        {this.state.type === 'recover' && (
                            <Recover
                                emailLabelHandler={this.emailLabelHandler}
                                passwordLabelHandler={this.passwordLabelHandler}
                                errorEmailHandler={this.errorEmailHandler}
                                errorPasswordHandler={this.errorPasswordHandler}
                                errorEmail={this.state.errorEmail}
                                errorPassword={this.state.errorPassword}
                            />
                        )}
                        <div className={'footer'}>
                            <div className={'enter'}>
                                <Button data={this.state.dataEnter}>
                                    {this.state.enterButtonText}
                                </Button>
                            </div>
                            <div className={'cancel'}>
                                <Button data={this.state.dataCancel}>
                                    Отмена
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>
                }
            </>
        )
    }
}