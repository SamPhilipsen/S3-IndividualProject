import React, { useState } from "react"
import { useHistory } from "react-router-dom";
import "../styling/login-styles.css"
import loadingGif from "../media/loading.gif"


import axios from "axios";

const Login = () => {
    const [errorText, setErrorText] = useState("");
    const [isLoading, setLoading] = useState(false)

    const [password, setPassword] = useState("")
    const [username, setUsername] = useState("")
    const history = useHistory();


    const handleLogin = () => {
        async function fetchData() {
            let startTime = new Date();
            setLoading(true);

            let data = JSON.stringify({
                "username": username,
                "password": password
            });

            try {
                let response = await axios.post("http://localhost:8080/login", data)
                const token = response.data;

                axios.defaults.headers.common['Authorization'] = token.Authorization;

                response = await axios.get('http://localhost:8080/members?name=' + username);
                localStorage.setItem('loggedInUser', JSON.stringify(response.data[0]));
                localStorage.setItem('authenticationToken', token.Authorization);

                history.push("/menu");

            } catch (error) {
                let endTime = new Date();
                let elapsedTime = (endTime - startTime)
                setErrorText("Could not establish a connection with the API! Elapsed time: " + elapsedTime + "ms");
                setLoading(false);
            }
        }
        fetchData();
    }

    let loadingStyle = {}

    if(!isLoading) {
        loadingStyle.display = "none";
    }
    const usernameChange = e => {
        setUsername(e.target.value)
    }

    const passwordChange = e => {
        setPassword(e.target.value)
    }

    return (
        <div className="loginScreen">
            <h1 className="network-error-text">
                {errorText}
            </h1>
            <div className="loginContainer">
                <h2>Username</h2>
                <input className="username-input"
                    type="text"
                    value={username}
                    onChange={usernameChange}
                />
                <h2>Password</h2>
                <input className="password-input"
                    type="text"
                    value={password}
                    onChange={passwordChange}
                />
                <br/>
                <br/>
                <br/>
                <button onClick={handleLogin} id="loginButton">
                    Log in
                </button>
                <img
                    style={loadingStyle}
                    className="loading-gif"
                    src={loadingGif}
                    alt="Loading..."
                    width="50"
                    height="50"
                />
            </div>
        </div>

    );
}

export default Login