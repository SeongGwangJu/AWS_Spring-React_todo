import React, { useState } from 'react';/** @jsxImportSource @emotion/react */
import * as S from './Style'
import { Link, Navigate } from 'react-router-dom';
import { signin } from '../../apis/api/account';

function Signin(props) {
    const emptyAccount = {
        name: "",
        email: "",
        password: ""
    }

    const [ account, setAccount ] = useState(emptyAccount);

    const handleInputChange = (e) => {
        setAccount({
            ...account,
            [e.target.name]: e.target.value
        });
    }


    const handleSigninSubmit = async () => {
        try {
            await signin(account);
            Navigate("/")
        }catch(error) {
            console.log("login중 Error 발생")
            console.log(error);
            const responseErrors = error.response.data
			const keyList = Object.keys(responseErrors);
        }
    }

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