import React, {useEffect, useState} from "react"
import { Route, Switch } from 'react-router-dom'
import Login from "../pages/Login";
import Header from "./Header";
import CointossContainer from "./CointossContainer";
import axios from "axios";
import Game2Container from "./Game2Container";

const MainSite = () => {
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('loggedInUser')));
    const [token] = useState(localStorage.getItem('authenticationToken'));
    axios.defaults.headers.common['Authorization'] = token;

    useEffect(() => {
        localStorage.setItem('loggedInUser', JSON.stringify(user))

        async function sendData() {

            try {
                const response = await axios.put("http://localhost:8080/members", user);
                return response
            } catch (error) {
                console.error(error);
            }
        }
        sendData();
    })

    const handlePointsChange = (newPoints) => {
        const totalPoints = user.points + newPoints;
        setUser({
            ...user,
            points: totalPoints,
        })
    }

    if(user) {
        return (
            <Switch>
                <Route exact path="/">
                    <Login />
                </Route>
                <Route path="/menu">
                    <div>
                        <Header
                            loggedInUser={user}
                        />
                        <CointossContainer
                            loggedInUser={user}
                            gamePointsChanged = {handlePointsChange}
                        />
                        <Game2Container
                            loggedInUser={user}
                            gamePointsChanged = {handlePointsChange}
                        />
                    </div>
                </Route>
            </Switch>

        );
    }
    return (
        <Login />
    )
}

export default MainSite