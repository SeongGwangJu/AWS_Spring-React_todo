import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import * as S from './Style'
import { signup } from '../../apis/api/account';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
function Signup(props) {

    const navigate = useNavigate();

    const user = {
        email: "",
        name: "",
        password: ""
    }

    const [ account, setAccount ] = useState(user);
    const [ errorMsg, setErrorMsg ] = useState("");

    const handleInputChange = (e) => {
        const { name, value } = e.target;

        setAccount({
            ...account,
            [name]: value
            // [e.target.name]: e.target.value //비구조화 대신 한줄로 표현 가능
        });
    }

    // const handleSignupSubmit = () => {
    //     axios.post("http://localhost:8080/auth/signup", {
    //         password: "1q2w3e4r",
    //         email: "aaa@gmail.com",
    //         name:"주성광"
    //     })
    //     navigate("accounts/login");
    // }

    const handleSignupSubmit = async () => {
        try{
            await signup(account);
            navigate("/accounts/login");

        }catch(error) {
            console.log("signup 중 Error 발생")
            console.log(error);

			const responseErrors = error.response.data
			const keyList = Object.keys(responseErrors);

            console.log("error.response.data")
            console.log(responseErrors)
            console.log("keyList")
            console.log(keyList)

            if(keyList.includes("email")) {
                alert(responseErrors.email);
            }else if(keyList.includes("password")){
                alert(responseErrors.password);
            }else if(keyList.includes("name")){
                alert(responseErrors.name);
            }else {
                alert("회원가입 실패")
            }
        }
    }

    return (
        <div css={S.SLayout}>
            <div>
                {errorMsg}
            </div>
            <h2>회원가입</h2>
            <input type="text" placeholder="email" name="email" onChange={handleInputChange} />
            <input type="text" placeholder="name" name="name" onChange={handleInputChange} />
            <input type="password" placeholder="password" name="password" onChange={handleInputChange} />
            <button onClick={handleSignupSubmit} >가입</button>
            <Link to="/accounts/login">
			    로그인
			</Link>
        </div>
    );
}

export default Signup;