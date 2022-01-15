import React, {useEffect, useState} from "react"
import { Route, Switch } from 'react-router-dom'
import Login from "../pages/Login";
import Header from "./Header";
import CointossContainer from "./CointossContainer";
import axios from "axios";
import Game2Container from "./Game2Container";

const MainSite = () => {
    const [user, setUser] = useState({});
    const [token] = useState(localStorage.getItem('authenticationToken'));
    axios.defaults.headers.common['Authorization'] = token;

    useEffect(() => {
        getUserData()
    }, [])

    async function getUserData() {
        try {
            const response = await axios.get("http://localhost:8080/members/0");
            setUser(response.data)
            localStorage.setItem('loggedInUser', JSON.stringify(response.data))
            console.log(response.data)
        } catch (error) {
            console.error(error);
        }
    }

    const handlePointsChange = () => {
        getUserData()
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
                        <br />
                        <br />
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