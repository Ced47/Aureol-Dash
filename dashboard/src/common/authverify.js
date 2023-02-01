import React from 'react'
import { Router } from 'react-router-dom'

const parseJwt= (token) =>
{
    try {
        return JSON.parse(atob(token.split(".")[1]));
    } catch (error) {
        return null;
    }
};

const authverify= (props) => {
    props.history.listen(() => {
        const user=JSON.parse(localStorage.getItem("user"));

        if (user) {
            const decodedJwt=parseJwt(user.accessToken);

            if (decodedJwt.exp*1000 < Date.now()) {
                props.logOut();
            }
        }
    });
    return <div>authverify</div>;
};

export default Router(authverify);

