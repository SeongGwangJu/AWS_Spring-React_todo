import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import * as S from './Style'
import { signup } from '../../apis/api/account';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
function Signup(props) {

    const navigate = useNavigate();

    const emptyAccount = {
        name: "",
        email: "",
        password: ""
    }
    
    const [ account, setAccount ] = useState(emptyAccount);
    const [ errorMsg, setErrorMsg ] = useState("");

    const handleInputChange = (e) => {
        setAccount({
            ...account,
            [e.target.name]: e.target.value
        });
    }

    const handleSignupSubmit = () => {
        axios.post("http://localhost:8080/auth/signup", {
            password: "1q2w3e4r",
            email: "aaa@gmail.com",
            name:"주성광"
        })
        navigate("accounts/login");
    }
    // const handleSignupSubmit = async () => {
    //     try{
    //         await signup(account);
    //         navigate("/accounts/login");

    //     }catch(error) {
	// 		const responseErrorMsg = error.response.data
	// 		const keys = Object.keys(responseErrorMsg);

    //         if(keys.includes("name")) {
	// 			setErrorMsg(responseErrorMsg.username);
	// 		}else if(keys.includes("email")) {
	// 			setErrorMsg(responseErrorMsg.phoneOrEmail);
	// 		}else if(keys.includes("password")) {
	// 			setErrorMsg(responseErrorMsg.password);
	// 		}
    //     }
    // }

    return (
        <div css={S.SLayout}>
            <div>
                {errorMsg}
            </div>
            <input type="text" placeholder="email" name="email" onChange={handleInputChange} />
            <input type="text" placeholder="name" name="name" onChange={handleInputChange} />
            <input type="password" placeholder="password" name="password" onChange={handleInputChange} />
            <button onClick={handleSignupSubmit} >가입</button>
        </div>
    );
}

export default Signup;