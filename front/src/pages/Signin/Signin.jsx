import React, { useState } from 'react';/** @jsxImportSource @emotion/react */
import * as S from './Style'
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { signin } from '../../apis/api/account';
import axios from 'axios';

function Signin(props) {

    const navigate = useNavigate();

    const user = {
        name: "",
        email: "",
        password: ""
    }

    const [ account, setAccount ] = useState(user);

    const handleInputChange = (e) => {
        console.log([e.target.name] + " : " + e.target.value)
        setAccount({
            ...account,
            [e.target.name]: e.target.value
        });
    }


    const handleSigninSubmit = async () => {
        try {
            await signin(account);
            navigate("/")
        }catch(error) {
            console.log("login중 Error 발생")
            console.log(error);
        }
    }

    // const handleSigninSubmit = async () => {
    //     try {
    //         await axios.post("http://localhost:8080/auth/signin", account);
    //     }catch(error) {
    //         console.error(error);
    //     }
    // }

    return (
        <div css={S.SLayout}>
            <h2>로그인</h2>
            <input type="text" placeholder="email" name="email" onChange={handleInputChange} />
            <input type="password" placeholder="password" name="password" onChange={handleInputChange} />
            <button onClick={handleSigninSubmit} >로그인</button>
            <Link to="/accounts/signup">
                회원가입
			</Link>
        </div>
    );
}

export default Signin;