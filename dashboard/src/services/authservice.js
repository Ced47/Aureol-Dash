import axios from 'axios'
//import React from 'react'

    const login=(username,password) => {
        return axios.post("localhost:8080/users/signin", 
        {
            username,password
        })
        .then((response) => 
        {
            if (response.data.username) {
                localStorage.setItem("user", JSON.stringify(response.data))
            }
            return response.data;
        });
    };

    const logout=()=>
    {
        localStorage.removeItem("user");
        return axios.post("localhost:8080/users/logout").then((response)=> 
        {
            return response.data;
        });
    };

    const getUser= ()=> 
    {
        return JSON.parse(localStorage.getItem("user"));
    };

    const authservice= {
        login,logout,getUser
    }

    export default authservice;

