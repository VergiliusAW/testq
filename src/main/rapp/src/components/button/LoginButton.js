import React from 'react'
import Button from './Button'

const data = {
    id: 1,
    text: "Войти",
    action: function () {
        console.log('Ещё не готово, подожди чутка и будет реализовано')
    }
}

function LoginButton() {
    return (
        <Button key={data.id} data={data}/>
    )
}

export default LoginButton