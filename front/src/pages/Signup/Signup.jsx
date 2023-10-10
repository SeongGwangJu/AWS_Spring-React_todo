import React from 'react';

const handleSignupSubmit = async () => {

}


function Signup(props) {
    return (
        <div>
            <input placeholder={"name"} name={"name"} />
            <input placeholder={"email"} name={"email"} />
            <input type={"password"} placeholder={"password"} name={"password"} />
            <button onClick={handleSignupSubmit} >가입</button>
        </div>
    );
}

export default Signup;