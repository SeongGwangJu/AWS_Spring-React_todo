import React, { useState } from 'react';
import { Link } from 'react-router-dom';
/** @jsxImportSource @emotion/react */
import * as S from './Style'
import axios from 'axios';
import { useQuery } from 'react-query';

function Home(props) {

    const [ content, setContent ] = useState("");
    const [ updateTodoState, setUpdateTodoState ] = useState(0);
    const [ updateContent, setUpdateContent ] = useState("");

    const todoList = useQuery(["todoList"], async () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        try {
            const response = await axios.get("http://localhost:8080/todo/list", option);
            console.log(response);
            return response;
        }catch(error) {
            console.error(error);
        }
    });

    const handleContentInputChange = (e) => {
        setContent(e.target.value);
    }

    const handleAddTodo = async () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        await axios.post("http://localhost:8080/todo", {content}, option);
        setContent("");
        todoList.refetch(); //데이터 변화에따라 자동으로 새로고침.
    }
    console.log(todoList)

    const handleDeleteTodo = async (todoId) => {
        // /todo/1
        try {
            const option = {
                headers: {
                    Authorization: localStorage.getItem("accessToken")
                }
        }
        await axios.delete(`http://localhost:8080/todo/${todoId}`, option);
        todoList.refetch();
        }catch(error) {
            console.error(error);
        }
    }

    const handleModifyTodo = async (todoId) => {
        if(updateTodoState == 0) {
            setUpdateTodoState(1)
        }else {
            setUpdateTodoState(0)
        }
    }

    const handleUpdateContentInputChange = (e) => {
        setUpdateContent(e.target.value);
    }

    const handleUpdateTodoSubmit = async (todoId) => {
        try {
            const option = {
                headers: {
                    Authorization: localStorage.getItem("accessToken")
                }
            }
            //updateContent 왜 있음?
            await axios.put(`http://localhost:8080/todo/${todoId}`, {updateContent}, option);
            todoList.refetch();
        }catch(error) {
            console.error(error);
        }

    }
    return (
        <>
            <h1 css={S.Sh1}>
                TODO
            </h1>
            <div>
                <input type="text" onChange={handleContentInputChange} value={content}/>
                <button onClick={handleAddTodo}> 추가 </button>
            </div>
            <ul>
                {todoList.isLoading ? "" : todoList?.data?.data && todoList?.data?.data.map(todo =>
                    <li key={todo.todoId}>
                        {todo.content}
                        {
                            updateTodoState === todo.todoId && (
                                <>
                                    <input type='text'
                                        value={updateContent}
                                        onChange={handleUpdateContentInputChange} />
                                    <button onClick={() => {
                                        if(todo.content !== updateContent) { //수정 전과 수정 후가 같지 않으면(수정해야할 때)
                                            handleUpdateTodoSubmit(todo.todoId)
                                        }
                                        setUpdateTodoState(0)
                                        setUpdateContent("")}}>확인</button>
                                </>
                            )
                        }
                        {
                            updateTodoState !== todo.todoId
                            ?  <button onClick={() => {
                                setUpdateTodoState(todo.todoId);
                                setUpdateContent(todo.content);
                            }}> 수정 </button>

                            :  <button onClick={() => {
                                setUpdateTodoState(0);
                                setUpdateContent("");
                            }}>취소\</button>
                        }
                        <button onClick={() => { handleDeleteTodo(todo.todoId); }}>삭제</button>
                    </li>
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