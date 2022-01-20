import React, {useEffect, useState} from "react"
import { Route, Switch } from 'react-router-dom'
import Login from "../pages/Login";
import Header from "./Header";
import CointossContainer from "./CointossContainer";
import axios from "axios";
import BlackjackContainer from "./BlackjackContainer";

const MainSite = () => {
    const [user, setUser] = useState();
    const [token] = useState(localStorage.getItem('authenticationToken'));
    axios.defaults.headers.common['Authorization'] = token;

    useEffect(() => {
        getUserData()
    }, [])

    async function getUserData() {
        try {
            const response = await axios.get("http://localhost:8080/members/" + localStorage.getItem("userId"));
            setUser(response.data)
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
                        <BlackjackContainer
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