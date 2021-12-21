import React, {useEffect, useState} from "react"
import "../styling/cointoss-styles.css"
import BettingComponent from "./BettingComponent";
import axios from "axios";

const CointossContainer = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [betAmount, setBetAmount] = useState();

    const [coinSide, setCoinSide] = useState("Heads");
    const [pointsText, setPointsText] = useState("");
    const [winningSideText, setWinningSideText] = useState("");

    useEffect(() => {
        setUser(props.loggedInUser);
    }, [props.loggedInUser])

    const flipCoinRequest = async () => {


    }

    const handleCointoss = e => {
        e.preventDefault()

        if(typeof betAmount == "number") {
            if(user.points > 0) {

                const data = {
                    'id': user.id,
                    'gameData': coinSide,
                    'pointsBet': betAmount
                }

                async function getData() {
                    try {
                        let preText;
                        const response = await axios.put("http://localhost:8080/games/cointoss", data)

                        setWinningSideText("The winning side is: " + response.data.gameData)

                        if(response.data.gameData === coinSide) {
                            preText = "You won! You earned "
                        } else {
                            preText = "You lose! You lost "
                        }
                        setPointsText(preText + betAmount + " points.")

                        props.gamePointsChanged(response.data.newPoints);
                    } catch (error) {
                        console.error(error);
                    }
                }
                getData()
            }
            else {
                setPointsText("You do not have sufficient points to play this game!")
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
                <p className="gameResultText">
                    {winningSideText}
                </p>
                <p className="gamePointsText">
                    {pointsText}
                </p>
            </form>

        </div>
    )
}
export default CointossContainer