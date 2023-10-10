import React, { useState } from 'react';/** @jsxImportSource @emotion/react */
import * as S from './Style'

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


    const handleSigninSubmit = () => {
    }

    return (
        <div css={S.SLayout}>
            <input type="text" placeholder="email" name="email" onChange={handleInputChange} />
            <input type="password" placeholder="password" name="password" onChange={handleInputChange} />
            <button onClick={handleSigninSubmit} >로그인</button>
        </div>
    );
}

export default Signin;