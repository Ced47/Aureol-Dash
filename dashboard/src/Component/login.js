import React,{useState} from 'react'
import axios from 'axios'
//import { useEffect  } from 'react'
import { Link } from 'react-router-dom'
import { GoogleLogin } from '@react-oauth/google'
import jwt_decode from 'jwt-decode'
//import { useState } from 'react'
import '../CSS/login.css'

function Login() {
   //<GoogleLogin></GoogleLogin>
   
   //
   const [username, setUsername]=useState("");
   const [password, setPassword]=useState("");
   /*const [error, setError]=useState("");
   //const [errorMessage, setErrorMessage]=useState("");

   // validation 
   const validName= (value) => {
       
   };

   const validEmail= (value) => {
       
   };

   const validPassword= (value) => {
       
   };*/

   // for username
   const onChangeUsername= (e) => {
       const username=e.target.value;
       setUsername(username);
   };

   // for name
   const onChangePassword= (e) => {
       const password=e.target.value;
       setPassword(password);
   };

   const handleSubmit = async (e)=> {
       e.preventDefault();
       e.stopPropagation();
       // error 
       //setError("");
       //setErrorMessage("");

       // traitement 
       axios
       .post("http://localhost:8080/users/signin", 
       {
           headers: {'Access-Control-Allow-Origin':true,},
           username, password
       })
       .then((response) => 
       {
           console.log(response.data);
           // eslint-disable-next-line no-unused-expressions
           response.data;

           window.location="/home";
       }) 
       .catch((eror) => 
       {
           console.log(eror);
       })
   };

  return (
    <section>
        <form onSubmit={handleSubmit} method='POST' className="register">
            <div className='title_page'>
                <div className='title'>
                    <h2>SE CONNECTER</h2>
                </div>
            </div>

            <div className='info'>
                <input value={username} type='text' placeholder='Username' onChange={onChangeUsername}/>
                <input value={password} type='password' placeholder='Mot de passe' onChange={onChangePassword}/>

                <button className='btn'><strong>SE CONNECTER</strong></button>
            </div>

            <div className='sign'>
                <Link to='/'><span className='p1'>S'inscire </span></Link>
            </div>

            <div className='sign_text1'>
                    <span>Ou</span>
            </div>

            <div className='sign1'>
                <div className='sign_text'>
                    <span>Se connecter avec un autre compte?</span>
                </div>

                <div className='sign_logo'>
                    <GoogleLogin
                    onSuccess={credentialResponse => {
                        console.log(credentialResponse.credential);
                        var decoded=jwt_decode(credentialResponse.credential)
                        console.log(decoded);
                        window.location="/home";
                    }}
                    onError={() => {
                        console.log('Login error');
                    }}
                    useOneTap 
                    />
                </div>
            </div>
        </form>
    </section>
  )
}
export default Login;
/* 
<div className='img1'>
                        <img src="/images/yammer.png" alt='yammer' className='img11'/>
                    </div>

                    <div className='img1'>
                        <img src="/images/outlook.png" alt='outlook' className='img22'/>
                    </div>

                    <div className='img1'>
                        <img src="/images/facebook.png" alt='outlook' className='img33'/>
                    </div>**/