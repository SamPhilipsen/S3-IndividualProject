import React, {useEffect, useState} from "react"
import { Route, Switch } from 'react-router-dom'
import Login from "../pages/Login";
import Header from "./Header";
import CointossContainer from "./CointossContainer";
import axios from "axios";

const MainSite = props => {
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('loggedInUser')));

    useEffect(() => {
        localStorage.setItem('loggedInUser', JSON.stringify(user))
        async function sendData() {
            try {
                const response = await axios.put("http://localhost:8080/users", user);
                return response
            } catch (error) {
                console.error(error);
            }
        }
        sendData();
    })

    const handlePointsChange = (newPoints) => {
        setUser({
            ...user,
            points: newPoints,
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