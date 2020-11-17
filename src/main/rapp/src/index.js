import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import ArticleList from './components/articles/ArticlesList'
import LoginButton from './components/button/LoginButton'
import Post from './components/post/Post'
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import Profile from './components/profile/Profile'

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
                <Route exact path={'/profile'}>
                    <Profile/>
                </Route>
            </Switch>
        </Router>
    </React.StrictMode>,
    document.getElementById('page-content')
);

ReactDOM.render(
    <React.StrictMode>
        <LoginButton/>
    </React.StrictMode>,
    document.getElementById('login')
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
