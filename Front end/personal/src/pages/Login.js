import React, { useState } from "react"
import { useHistory } from "react-router-dom";
import "./login-styles.css"

import axios from "axios";

const Login = () => {
    const [errorText, setErrorText] = useState("");
    const history = useHistory();

    const handleLogin = () => {
        async function fetchData() {
            let startTime = new Date();
            try {
                const response = await axios.get("http://localhost:8080/users?name=Peter");
                localStorage.setItem('loggedInUser', JSON.stringify(response.data[0]));
                history.push("/menu");
            } catch (error) {
                let endTime = new Date();
                let elapsedTime = (endTime - startTime)
                setErrorText("Could not establish a connection with the API! Elapsed time: " + elapsedTime + "ms");
            }

        }
        fetchData();
    }

    return (
        <div className="loginContainer">
            <h1 className="network-error-text">
                {errorText}
            </h1>
            <button onClick={handleLogin} id="loginButton">
                Log in
            </button>
        </div>
    );
}

export default Login