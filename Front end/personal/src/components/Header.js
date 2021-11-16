import React, { useState, useEffect } from "react"
import { useHistory } from "react-router-dom";
import "./header-styles.css"

const Header = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const history = useHistory()

    useEffect(() => {
        setUser(props.loggedInUser);
    }, [props.loggedInUser])

    const handleLogout = () => {
        setUser([]);
        localStorage.removeItem('loggedInUser')
        localStorage.removeItem('authenticationToken')
        history.push("");
    }

    return (
        <header>
            <div className="userInfoHeader">
                <ul className="userInformation">
                    <li key={user.id}>Name: {user.username}</li>
                    <li>Points: {user.points}</li>
                    <li><button id="logoutButton" onClick={handleLogout}>Log out</button></li>
                </ul>
            </div>
        </header>
    )
}
export default Header