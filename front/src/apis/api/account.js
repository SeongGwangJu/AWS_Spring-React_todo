import instance from "../../utils/instance";

export const signup = async (account) => {
    const response = await instance.post("/api/v1/auth/user", account);
    console.log(response);
    return response;
}