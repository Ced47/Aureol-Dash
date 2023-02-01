import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { GoogleOAuthProvider } from '@react-oauth/google'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Login from './Component/login';
import Home from './Component/home';

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <GoogleOAuthProvider clientId='364358153374-r5447l7nlv3bc574ebkug7mjagi3c88i.apps.googleusercontent.com'>
      <Router>
        <Routes>
          <Route path='/' element={<App/>} />
          <Route path='/login' element={<Login/>} />
          <Route path='/home' element={<Home/>} />
        </Routes>
      </Router>  
    </GoogleOAuthProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
