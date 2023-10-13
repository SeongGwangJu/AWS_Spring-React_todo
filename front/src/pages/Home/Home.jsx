import React, { useState } from 'react';
import { Link } from 'react-router-dom';
/** @jsxImportSource @emotion/react */
import * as S from './Style'
import axios from 'axios';

function Home(props) {

    const [content, setContent ] = useState("");

    const handleInputChange = (e) => {
        setContent(e.target.value);
    }

    const handleAddTodo = () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        axios.post("http://localhost:8080/todo", { content }, option);
    }

    return (
        <>
            <h1 css={S.Sh1}>
                TODO
            </h1>
            <div>
                <input type="text" onChange={handleInputChange}/>
                <button onClick={handleAddTodo}> 추가 </button>
            </div>
            <ul>
                <li>
                    1
                </li>
            </ul>
            <h4>
                <Link to="/auth/signup">
                    회원가입 창으로 이동
                </Link>
            </h4>

            <h4>
                <Link to="/auth/signin">
                    로그인 창으로 이동
                </Link>
            </h4>
        </>
    );
}

export default Home;