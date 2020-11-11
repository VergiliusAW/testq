import React from 'react'
import './Button.css'

function Button(props) {
    return (
        <div className={'button'} onClick={props.data.action}>
            <span>{props.children}</span>
        </div>
    )
}

export default Button