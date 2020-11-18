import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import ArticleList from './components/articles/ArticlesList'
import LoginButton from './components/button/LoginButton'
import Post from './components/post/Post'
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import Profile from './components/profile/Profile'
import BorI from './components/button/BorI'

ReactDOM.render(
    <React.StrictMode>
        <BorI/>
    </React.StrictMode>,
    document.getElementById('login')
)

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Switch>
                <Route exact path={'/'}>
                    <ArticleList/>
                </Route>
                <Route exact path={'/post/*'}>
                    <Post/>
                </Route>
                <Route exact path={'/profile/*'}>
                    <Profile/>
                </Route>
            </Switch>
        </Router>
    </React.StrictMode>,
    document.getElementById('page-content')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
