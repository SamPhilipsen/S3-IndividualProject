import React, { useState, useEffect } from "react"
import axios from "axios";

const client = axios.create({
    baseURL: "http://localhost:8080/users?name=Peter"
});

const MainSite = () => {
    const [user, setUser] = useState([]);

    useEffect( () => {
    }, []);

    const handleLogin = () => {
        async function fetchData() {
            const response = await client.get("");
            setUser(response.data);
        }
        fetchData();
    }

    if(!user) return null

    return (
        <div>

            {console.log(user)}
            {user.map(data => (
                <h1>Welcome, {data.name}</h1>
                ))}
            {user.map(data => (
                <h2>ID: {data.id}</h2>
            ))}
            {user.map(data => (
                <h2>Points: {data.points}</h2>
            ))}
            <button onClick={handleLogin}>
                Log in
            </button>
        </div>
    );
}

export default MainSite