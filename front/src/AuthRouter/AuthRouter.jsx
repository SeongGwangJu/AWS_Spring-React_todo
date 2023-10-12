import axios from 'axios';
import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

function AuthRouter({element}) {

    const location = useLocation();
    const pathname = location.pathname;
    const permitAllPath = ["/auth"];

    useEffect(() => {
        const option = {
            headers :{
                Authorization: localStorage.getItem("accessToken")
            }
        }
        axios.get("http://localhost:8080/auth/authenticated", option);
    });

    return element;
}
export default AuthRouter;