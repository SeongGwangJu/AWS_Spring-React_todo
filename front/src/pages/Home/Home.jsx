import React from 'react';
import { Link } from 'react-router-dom';
/** @jsxImportSource @emotion/react */
import * as S from './Style'

function Home(props) {
    return (
        <>
            <h1 css={S.Sh1}>
                TODO
            </h1>
            
            <ul>
                <li>
                    1
                </li>
            </ul>
            <h4>
                <Link to="/accounts/signup">
                    회원가입 창으로 이동
                </Link>
            </h4>

            <h4>
                <Link to="/accounts/login">
                    로그인 창으로 이동
                </Link>
            </h4>
        </>
    );
}

export default Home;