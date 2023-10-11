import axios from "axios";
import instance from "../../utils/instance";

export const signup = async (account) => {
    // const response = await axios.post("http://localhost:8080/auth/signup", account);
    const response = await instance.post("/auth/signup", account)
    console.log(response);
    return response;
}

export const signin = async (account) => {
    const response = await instance.post("/auth/signin", account)
    console.log("login 응답")
    console.log(response)
}