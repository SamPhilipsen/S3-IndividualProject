import React, {useState, useEffect} from "react";
import "../styling/game2-styles.css"
import BettingComponent from "./BettingComponent";
import axios from "axios";

const Game2Container = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [pointsText, setPointsText] = useState("")
    const [resultText, setResultText] = useState("")
    const [betAmount, setBetAmount] = useState()

    const [gameId, setGameId] = useState()
    const [cardDeck, setCardDeck] = useState([])
    const [playerCards, setPlayerCards] = useState([])
    const [dealerCards, setDealerCards] = useState([])

    const [isPlaying, setPlaying] = useState(false)

    /*Dealer gives every player, including himself a card face up.
    Then, everyone gets another card also face up, except for the dealer's which will be hidden.
    If a player's cards total to 21 already by this point, they receive 1.5x their bet from the dealer.
    Any players who did not get this plays further.
    The players can call for a hit to receive an extra card. There is no limit to the amount of times, but if the cards total to more than 21 they bust and lose their bet to the dealer.
    A player can choose to stand at any point while they are still playing to signal they do not want to draw any more cards.
    If the player gets an ace, it can be either 1 or 11.
    Once everyone is done, the dealer reveals their second card.
    If the value of the dealer's cards is 16 or lower, they have to draw an extra card.
    If the value of the dealer's cards is 17 or higher, they stand.
    If the dealer busts due to the value of cards being higher than 21, everyone still in the game wins twice their bet.
    If the dealer does not bust, and cannot draw any more cards, the players who's card values are higher than that of the dealer win twice their bet.
    The rest lose their entire bet.*/

    const playerHits = () => {
        playerPerformsAction("hit")
    }

    const playerStands = () => {
        playerPerformsAction("stand")
    }

    function playerPerformsAction(action) {
        const data = {
            gameId: gameId,
            action: action,
        }
        async function getCard() {
            try {
                const response = await axios.put("http://localhost:8080/games/blackjack", data);
                if(response.status === 200) {
                    setCardDeck(response.data.cardDeck)
                    setDealerCards(response.data.dealerCards)
                    setPlayerCards(response.data.playerCards)
                    if(response.data.winner === "player") {
                        setResultText("You won!")
                        setPlaying(false)
                    }
                    if(response.data.winner === "dealer") {
                        setResultText("You lost!")
                        setPlaying(false)
                    }
                    props.gamePointsChanged();
                }
            } catch (error) {
                console.error(error);
            }
        }
        getCard()
    }

    const resetGame = () => {
        setPlaying(false)
    }

    const handleGameStart = e => {
        e.preventDefault()
        if (typeof betAmount == "number") {
                const data = {
                    bet: betAmount,
                    playerId: user.id,
                }
                async function sendData() {
                        await axios.post("http://localhost:8080/games/blackjack", data)
                            .then((response) => {
                                setCardDeck(response.data.cardDeck)
                                setDealerCards(response.data.dealerCards)
                                setPlayerCards(response.data.playerCards)
                                setGameId(response.data.id)
                                setPlaying(true);
                                setPointsText("");
                                setResultText("");
                                props.gamePointsChanged()
                            })
                            .catch((error) => {
                            if(error.response.status === 400) {
                                setPointsText("You do not have enough points to play")
                            }
                        })
                }
                sendData()
        } else {
            alert("Input has to be a number!")
        }
    }

    const setBet = (amount) => {
        setBetAmount(amount)
    }

    let playMode = {}
    let betMode = {}

    if(isPlaying) {
        betMode.display = "none"
    } else {
        playMode.display = "none"
    }

    return (
        <div className="gameBaseContainer">
            <div className="game2container">
                <h1>Blackjack</h1>
                <form onSubmit={handleGameStart} className="game2StartContainer" style={betMode}>
                    <BettingComponent
                        defineBet = {setBet}
                    />
                    <button input="submit" className="game-submit">
                        Start game
                    </button>
                </form>

                <div className="controlButtons" style={playMode}>
                    <button onClick={playerHits}>
                        Hit
                    </button>
                    <button onClick={playerStands}>
                        Stand
                    </button>
                </div>

                <div className="cardsDisplay">
                    <div className="playerCardsDisplay">
                        <h2>Your cards:</h2>
                        {playerCards.map(card => {
                            return <h3 key={card.name}>{card.name}</h3>
                        })}
                    </div>
                    <div className="dealerCardsDisplay">
                        <h2>Dealer cards:</h2>
                        {dealerCards.map(card => {
                            return <h3 key={card.name}>{card.name}</h3>
                        })}
                    </div>
                </div>
                <p className="gameResultText">
                    {resultText}
                </p>
                <p className="gamePointsText">
                    {pointsText}
                </p>
            </div>
        </div>
    )
}
export default Game2Container