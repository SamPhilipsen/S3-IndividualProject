import React, {useEffect, useState} from "react"
import "./cointoss-styles.css"

const CointossContainer = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [betAmount, setBetAmount] = useState();
    const [coinSide, setCoinSide] = useState("Heads");
    const [winText, setWinText] = useState("");
    const [winningSideText, setWinningSideText] = useState("");

    useEffect(() => {
        setUser(props.loggedInUser);
    }, [props.loggedInUser])

    const handleCointoss = e => {
        e.preventDefault()
        var newPoints = 0;
        var sides = ['Heads', 'Tails'];

        if(typeof betAmount == "number") {
            if(user.points > 0 && user.points - betAmount > 0) {
                let result = sides[Math.floor(Math.random()*sides.length)]

                if(result === coinSide) {
                    newPoints = user.points + betAmount;
                    setWinText("You win! You receive " + betAmount + " points. Your new balance is: " + newPoints + "!")
                } else {
                    newPoints = user.points - betAmount;
                    setWinText("You lose! You lost " + betAmount + " points. Your new balance is: " + newPoints + "!")
                }
                setWinningSideText("The winning side is: " + result)
                props.gamePointsChanged(newPoints);
            }
            else {
                setWinText("You do not have sufficient points to play this game! (You have " + user.points + ", you need at least " + (betAmount + 1) + " to play.)")
            }
        } else {
            alert("Input can only be a number!");
        }
    }

    const onBetChange = e => {
        var amount = parseInt(e.target.value)
        if (!isNaN(amount)) {
            setBetAmount(parseInt(amount))
        } else {
            setBetAmount("");
        }
    }

    const rbChanged = e => {
        setCoinSide(e.target.value)
    }

    return (
        <div className="coinTossContainer">
            <form onSubmit={handleCointoss} className="coinTossForm">
                <input
                    type="text"
                    className="bet-input"
                    placeholder="Points you want to bet"
                    value={betAmount}
                    name="bet-points"
                    onChange={onBetChange}
                />
                <input
                    type="radio"
                    value="Heads"
                    name="bet-coinside"
                    checked={coinSide === "Heads"}
                    onChange={rbChanged}
                />
                Heads
                <input
                    type="radio"
                    value="Tails"
                    name="bet-coinside"
                    checked={coinSide === "Tails"}
                    onChange={rbChanged}
                />
                Tails
                <button input="submit" className="bet-submit">
                    Flip
                </button>
            </form>
            <p>{winningSideText}</p>
            <p>{winText}</p>
        </div>
    )
}
export default CointossContainer