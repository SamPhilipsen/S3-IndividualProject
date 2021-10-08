import React, { useState, useEffect } from "react"
import axios from "axios";

const client = axios.create({
    baseURL: "http://localhost:8080/users?name=Peter"
});

let loggedIn = false;

const MainSite = () => {
    const [user, setUser] = useState([]);
    const [winText, setWinText] = useState("");

    useEffect( () => {
    }, []);

    const handleLogin = () => {
        async function fetchData() {
            const response = await client.get("");
            setUser(response.data);
        }
        fetchData();
        loggedIn = true;
    }

    //Could not get this to work.
    /*const gameResultHandling = () => {
        async function sendData() {
            const response = await axios.post("http://localhost:8080/users", {user})
        }
    }*/

    const handleCointoss = () => {
        let coinSide;

        let resultNumber = Math.floor(Math.random() * 2);
        if(resultNumber === 0) {
            coinSide = "heads";
            setWinText("You win! " + coinSide)
        } else {
            coinSide = "tails";
            setWinText("You lose! " + coinSide)
        }
    }

    let loginButtonView = {}
    let coinTossView = {}

    if(loggedIn) {
        loginButtonView.display = "none"
    } else {
        coinTossView.display = "none"
    }

    if(!user) return null

    return (
        <div>
            {user.map(data => (
                <h1>Welcome, {data.name}</h1>
                ))}
            {user.map(data => (
                <h2>ID: {data.id}</h2>
            ))}
            {user.map(data => (
                <h2>Points: {data.points}</h2>
            ))}
            <button onClick={handleLogin} style={loginButtonView}>
                Log in
            </button>
            <div className="coinTossCointainer" style={coinTossView}>
                <p>Default coin side is <b>Heads</b>. Win = +50 points, lose = -50 points</p>
                <button onClick={handleCointoss}>
                    Flip coin
                </button>
                <p>{winText}</p>
            </div>
        </div>
    );
}

export default MainSite