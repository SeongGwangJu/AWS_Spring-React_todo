import React, { useState } from 'react';
import { Link } from 'react-router-dom';
/** @jsxImportSource @emotion/react */
import * as S from './Style'
import axios from 'axios';
import { useQuery } from 'react-query';

function Home(props) {

    const [content, setContent ] = useState("");

    const todoList = useQuery(["todoList"], async () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        try {
            const response = await axios.get("http://localhost:8080/todo/list", option);
            return response;
        }catch(error) {
            console.error(error);
        }
    });

    const handleInputChange = (e) => {
        setContent(e.target.value);
    }

    const handleAddTodo = async () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        await axios.post("http://localhost:8080/todo", {content}, option);
        todoList.refetch(); //데이터 변화에따라 자동으로 새로고침.
    }
    console.log(todoList)

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
                {todoList.isLoading ? "" : todoList?.data?.data && todoList?.data?.data.map(todo =>
                    <li key={todo.todoId}>{todo.content} </li>
                )}
            </ul>

            {/* <h4>
                <Link to="/auth/signup">
                    회원가입 창으로 이동
                </Link>
            </h4>

            <h4>
                <Link to="/auth/signin">
                    로그인 창으로 이동
                </Link>
            </h4> */}
        </>
    );
}

export default Home;