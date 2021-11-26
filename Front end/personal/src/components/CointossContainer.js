import React, {useEffect, useState} from "react"
import "../styling/cointoss-styles.css"
import BettingComponent from "./BettingComponent";

const CointossContainer = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [betAmount, setBetAmount] = useState();

    const [coinSide, setCoinSide] = useState("Heads");
    const [pointsText, setPointsText] = useState("");
    const [winningSideText, setWinningSideText] = useState("");

    useEffect(() => {
        setUser(props.loggedInUser);
    }, [props.loggedInUser])

    const handleCointoss = e => {
        e.preventDefault()
        var newPoints;
        var sides = ['Heads', 'Tails'];

        if(typeof betAmount == "number") {
            if(user.points > 0 && user.points - betAmount > 0) {
                let result = sides[Math.floor(Math.random()*sides.length)]

                if(result === coinSide) {
                    newPoints = betAmount;
                    setPointsText("You win! You receive " + betAmount + " points.")
                } else {
                    newPoints = -Math.abs(betAmount)
                    setPointsText("You lose! You lost " + betAmount + " points.")
                }
                setWinningSideText("The winning side is: " + result)
                props.gamePointsChanged(newPoints);
            }
            else {
                setPointsText("You do not have sufficient points to play this game! (You have " + user.points + ", you need at least " + (betAmount) + " to play.)")
            }
        } else {
            alert("Input can only be a number!");
        }
    }

    const rbChanged = e => {
        setCoinSide(e.target.value)
    }

    const setBet = (amount) => {
        setBetAmount(amount);
    }

    return (
        <div className="gameBaseContainer">
            <h1>Cointoss</h1>
            <form onSubmit={handleCointoss} className="coinTossForm">
                <BettingComponent
                    defineBet = {setBet}
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
                <p>{winningSideText}</p>
                <p>{pointsText}</p>
            </form>

        </div>
    )
}
export default CointossContainer