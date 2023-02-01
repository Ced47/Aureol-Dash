import React, {useState} from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
//import {Form, Field} from 'react-final-form'
import { isEmail } from "validator"
//import response from 'response';
import '../App.css'

export default function Register() {
        //
        const [name, setName]=useState("");
        const [username, setUsername]=useState("");
        const [email, setEmail]=useState("");
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

        // for name
        const onChangeName= (e) => {
            const name=e.target.value;
            setName(name);
            if (name.length <1 || name.length >20) {
                return (
                    <div className='invalid-feedback d-block' role={alert}>
                        The username must be between 1 and 20 characters.
                    </div>);
            }
        };

        // for username
        const onChangeUsername= (e) => {
            const username=e.target.value;
            setUsername(username);
            if (username.length <1 || username.length >20) {
                return (
                    <div className='invalid-feedback d-block' role={alert}>
                        The username must be between 1 and 20 characters.
                    </div>);
            }
        };

        // for email
        const onChangeEmail= (e) => {
            const email=e.target.value;
            setEmail(email);
            if (!isEmail(email)) {
                return (
                        alert("The Email is not valid.")
            )}
        };

        // for name
        const onChangePassword= (e) => {
            const password=e.target.value;
            setPassword(password);
            if (password.length <8 || password.length >40) {
                return (
                    alert("The password must be between 8 and 40 characters")
            )}
        };

        const handleSubmit = async (e)=> {
            e.preventDefault();
            e.stopPropagation();
            // error 
            //setError("");
            //setErrorMessage("");
    
            // traitement 
            axios
            .post("http://localhost:8080/users", 
            {
                headers: {'Access-Control-Allow-Origin':true,},
                name, username, email, password
            })
            .then((response) => 
            {
                console.log(response.data);
                // eslint-disable-next-line no-unused-expressions
                response.data;
    
                window.location="/login";
            }) 
            .catch((eror) => 
            {
                console.log(eror);
            })
        };
    

    

  return (
    <section>
        <form method='POST' onSubmit={handleSubmit} className="register">
            <div className='title_page'>
                <div className='title'>
                    <h2>S'INSCRIRE</h2>
                </div>
            </div>

            <div className='informations'>
                <input value={name} type='text' placeholder='Nom' onChange={onChangeName} required />
                <input value={username} type='text' placeholder='Username' onChange={onChangeUsername} required/>
                <input value={email} type='text' placeholder='Email' onChange={onChangeEmail} required/>
                <input value={password} type='password' placeholder='Mot de passe' onChange={onChangePassword} required/>

                <button type='submit' className='btn' ><strong>S'INSCRIRE</strong></button>
            </div>

            <div className='sign_in'>
                <Link to='/login'>
                   <span>Se connecter avec un compte?</span>
                </Link>
            </div>
        </form>
    </section>
  )
}
