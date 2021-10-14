import React from "react"
import { useHistory } from "react-router-dom";

import axios from "axios";

const Login = () => {
    const history = useHistory();

    const handleLogin = () => {
        async function fetchData() {
            const response = await axios.get("http://localhost:8080/users?name=Peter");
            history.push("/menu", response.data[0]);
        }
        fetchData();
    }

    return (
        <div className="loginContainer">
            <button onClick={handleLogin} id="loginButton">
                Log in
            </button>
        </div>
    );
}

export default Login