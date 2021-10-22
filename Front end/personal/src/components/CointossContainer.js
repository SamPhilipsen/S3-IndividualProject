import React, {useEffect, useState} from "react"

const CointossContainer = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [winText, setWinText] = useState("");

    useEffect(() => {
        setUser(props.loggedInUser);
    }, [props.loggedInUser])

    const handleCointoss = () => {
        let coinSide;
        var newPoints = 0;

        if(user.points > 0) {
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
            props.gamePointsChanged(newPoints);
        }
        else {
            setWinText("You do not have sufficient points to play this game! (You have " + user.points + ", you need 50 to play.)")
        }

    }

    return (
        <div className="coinTossContainer">
            <p>Default coin side is <b>Heads</b>. Win = +50 points, lose = -50 points</p>
            <button onClick={handleCointoss}>
                Flip coin
            </button>
            <p>{winText}</p>
        </div>
    )
}
export default CointossContainer