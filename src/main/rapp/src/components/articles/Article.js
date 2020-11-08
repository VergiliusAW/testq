import React from 'react'
import './Article.css'

function Article(props) {
    return (
        <div className={'lb'}>
        <a href={'post/'+props.data.id} className={"card"}>
            <h3>{props.data.title}</h3>
            <p>{props.data.body}</p>
        </a>
        </div>
    )
}

export default Article