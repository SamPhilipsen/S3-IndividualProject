import React, { useState, useEffect } from "react"
import axios from "axios";
import {Route, Switch, useLocation } from 'react-router-dom'
import Login from "../pages/login";

const MainSite = () => {
    const [user, setUser] = useState([]);
    const [winText, setWinText] = useState("");
    const location = useLocation();

    useEffect(() => {
            setUser(location.state);
    }, [location.state])


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

    if(!user) return (
        <Login />   
    )

    return (
            <Switch>
                <Route exact path="/">
                    <Login />
                </Route>
                <Route path="/menu">
                    <div>
                        <ul className="userInformation">
                            <li>Name: {user.name}</li>
                            <li key={user.id}>Id: {user.id}</li>
                            <li>Points: {user.points}</li>
                        </ul>

                        <div className="coinTossContainer">
                            <p>Default coin side is <b>Heads</b>. Win = +50 points, lose = -50 points</p>
                            <button onClick={handleCointoss}>
                                Flip coin
                            </button>
                            <p>{winText}</p>
                        </div>
                    </div>
                </Route>
            </Switch>

    );
}

export default MainSite