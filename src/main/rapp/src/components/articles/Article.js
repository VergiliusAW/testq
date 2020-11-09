import React from 'react'
import './Article.css'
import {Link} from 'react-router-dom'

function Article(props) {
    return (
        <div className={'lb'}>
            <Link to={'post/' + props.data.id} className={"card"}>
                <h3>{props.data.title}</h3>
                <p>{props.data.preview}</p>
            </Link>
        </div>
    )
}

export default Article