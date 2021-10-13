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
            setUser(response.data[0]);
        }
        fetchData();
        loggedIn = true;
    }

    //Could not get this to work.
    const gameResultHandling = () => {
        async function sendData() {
            try {
                const response = await axios.put("http://localhost:8080/users", user);
                return response
            } catch (error) {
                console.error(error);
            }
        }
        sendData().then(r => console.log());
    }

    const handleCointoss = () => {
        let coinSide;
        var newPoints;

        let resultNumber = Math.floor(Math.random() * 2);
        if(resultNumber === 0) {
            coinSide = "heads";
            newPoints = user.points + 50;
            setWinText("You win! " + coinSide)
        } else {
            coinSide = "tails";
            newPoints = user.points - 50;
            setWinText("You lose! " + coinSide)
        }
        setUser({
            ...user,
            points: newPoints,
        })
        gameResultHandling()
    }

    let loginButtonView = {}
    let coinTossView = {}
    let loggedInInformation = {}

    if(loggedIn) {
        loginButtonView.display = "none"
    } else {
        coinTossView.display = "none"
        loggedInInformation.display = "none"
    }

    if(!user) return null

    return (
        <div>
            <button onClick={handleLogin} style={loginButtonView}>
                Log in
            </button>

            <div style={loggedInInformation}>
                <h1>welcome, {user.name}</h1>
                <h2 key={user.id}>id: {user.id}</h2>
                <h2>points: {user.points}</h2>

                <div className="coinTossCointainer" style={coinTossView}>
                    <p>Default coin side is <b>Heads</b>. Win = +50 points, lose = -50 points</p>
                    <button onClick={handleCointoss}>
                        Flip coin
                    </button>
                    <p>{winText}</p>
                </div>
            </div>
        </div>
    );
}

export default MainSite