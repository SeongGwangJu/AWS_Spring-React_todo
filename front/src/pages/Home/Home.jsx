import React from 'react';
import { Link } from 'react-router-dom';

function Home(props) {
    return (
        <div>
            <h1>
                Hello
            </h1>
            
            <Link to="/accounts/signup">
                회원가입
			</Link>

            <Link to="/accounts/login">
                로그인으로 이동
			</Link>
        </div>
    );
}

export default Home;