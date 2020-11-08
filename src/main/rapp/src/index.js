import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import ArticleList from './components/articles/ArticlesList'
import LoginButton from "./components/button/LoginButton";

ReactDOM.render(
  <React.StrictMode>
      <ArticleList/>
  </React.StrictMode>,
  document.getElementById('articles')
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
