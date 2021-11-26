import React, {useState, useEffect} from "react";
import "../styling/game2-styles.css"
import BettingComponent from "./BettingComponent";

const Game2Container = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [pointsText, setPointsText] = useState("")
    const [resultText, setResultText] = useState("")

    const [betAmount, setBetAmount] = useState();

    const initialCardDeck = [
        {name: "hearts 2", value: 2},
        {name: "hearts 3", value: 3},
        {name: "hearts 4", value: 4},
        {name: "hearts 5", value: 5},
        {name: "hearts 6", value: 6},
        {name: "hearts 7", value: 7},
        {name: "hearts 8", value: 8},
        {name: "hearts 9", value: 9},
        {name: "hearts 10", value: 10},
        {name: "hearts jack", value: 10},
        {name: "hearts queen", value: 10},
        {name: "hearts king", value: 10},
        {name: "spades 2", value: 2},
        {name: "spades 3", value: 3},
        {name: "spades 4", value: 4},
        {name: "spades 5", value: 5},
        {name: "spades 6", value: 6},
        {name: "spades 7", value: 7},
        {name: "spades 8", value: 8},
        {name: "spades 9", value: 9},
        {name: "spades 10", value: 10},
        {name: "spades jack", value: 10},
        {name: "spades queen", value: 10},
        {name: "spades king", value: 10},
        {name: "diamonds 2", value: 2},
        {name: "diamonds 3", value: 3},
        {name: "diamonds 4", value: 4},
        {name: "diamonds 5", value: 5},
        {name: "diamonds 6", value: 6},
        {name: "diamonds 7", value: 7},
        {name: "diamonds 8", value: 8},
        {name: "diamonds 9", value: 9},
        {name: "diamonds 10", value: 10},
        {name: "diamonds jack", value: 10},
        {name: "diamonds queen", value: 10},
        {name: "diamonds king", value: 10},
        {name: "clubs 2", value: 2},
        {name: "clubs 3", value: 3},
        {name: "clubs 4", value: 4},
        {name: "clubs 5", value: 5},
        {name: "clubs 6", value: 6},
        {name: "clubs 7", value: 7},
        {name: "clubs 8", value: 8},
        {name: "clubs 9", value: 9},
        {name: "clubs 10", value: 10},
        {name: "clubs jack", value: 10},
        {name: "clubs queen", value: 10},
        {name: "clubs king", value: 10},
    ]
    const [cardDeck, setCardDeck] = useState(initialCardDeck)
    const [playerCards, setPlayerCards] = useState([])
    const [dealerCards, setDealerCards] = useState([])

    const [compareCards, setComparingCards] = useState(false);

    const [isPlaying, setPlaying] = useState(false)

    useEffect(() => {
        setUser(props.loggedInUser)
    }, [props.loggedInUser])

    useEffect(() => {
        const cardsValue = getPlayerCardsValue()

        console.log("Player cards value = " + cardsValue)

        if (cardsValue > 21) {
            onLoss()
        }

    },[playerCards])

    useEffect(() => {
        const dealerCardsValue = getDealerCardsValue()

        console.log("Dealer cards value = " + dealerCardsValue)

        if(compareCards) {

            const playerCardsValue = getPlayerCardsValue()

            if (dealerCardsValue > 21) {
                onWin()
            } else if (dealerCardsValue <= 21) {
                if(playerCardsValue > dealerCardsValue) {
                    onWin()
                }
                if(playerCardsValue < dealerCardsValue) {
                    onLoss()
                }
                if(playerCardsValue === dealerCardsValue) {
                    alert("Both card values are equal.")
                }
            }
        }
    }, [dealerCards])

    const drawCards = (cardAmount) => {

        if(cardDeck.length >= cardAmount) {
            let results = [];

            for(let i = 0; i < cardAmount; i++) {
                let result = cardDeck[Math.floor(Math.random()*cardDeck.length)];
                results.map((card) => {
                    if(card === result) {
                        result = cardDeck[Math.floor(Math.random()*cardDeck.length)];
                    }
                })
                results.push(result);
            }

            let newDeck = cardDeck.filter((card) => {
                if(!results.includes(card)) { return card; }
            })

            if(results != null) {
                setCardDeck(newDeck)
                return results
            }
        } else {
            console.log("Attempting to draw more cards than there are in the card deck.")
            return false;
        }
    }

    /*
    Dealer gives every player, including himself a card face up.
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
    The rest lose their entire bet.
    */

    const getPlayerCardsValue = () => {
        let cardsValue = 0;
        playerCards.map((card) =>
            cardsValue += card.value
        );
        return cardsValue
    }

    const getDealerCardsValue = () => {
        let cardsValue = 0;
        dealerCards.map((card) =>
            cardsValue += card.value
        );
        return cardsValue
    }

    const playerDrawsCard = () => {
        async function getCard() {
            let card = drawCards(1)
            await setPlayerCards(playerCards => [
                ...playerCards, card[0]
            ])
        }
        getCard()
    }

    const playerStands = () => {
        let newDeck = dealerCards.map((card) => {
            return card;
        })

        function getCardsValue() {
            let value = 0;
            newDeck.map((card) => {
                value += card.value;
            })
            return value;
        }

        function checkDealerResults() {
            const cardsValue = getCardsValue();
            if(cardsValue <= 16 )
            {
                let card = drawCards(1)
                newDeck.push(card[0])
                checkDealerResults()
            }

            setComparingCards(true);
            setDealerCards(newDeck)
        }
        checkDealerResults()
    }

    const onWin = () => {
        setResultText("You won! You receive " + betAmount + " points.")
        props.gamePointsChanged(betAmount)
        resetGame()
    }

    const onLoss = () => {
        setResultText("You lost! You lost " + betAmount + " points.")
        const newPoints = -Math.abs(betAmount)
        props.gamePointsChanged(newPoints)
        resetGame()
    }

    const resetGame = () => {
        setPlaying(false)
        setComparingCards(false)
    }

    const handleGameStart = e => {
        e.preventDefault()
        if (typeof betAmount == "number") {
            if (user.points > 0 && user.points - betAmount > 0) {
                setDealerCards([]);
                setPlayerCards([]);
                setCardDeck(initialCardDeck);
                setPlaying(true);
                setPointsText("");
                setResultText("");

                let cards = drawCards(4)
                if (cards) {
                    setPlayerCards(playerCards => [
                        ...playerCards, cards[0], cards[1]
                    ])
                    setDealerCards(dealerCards => [
                        ...dealerCards, cards[2], cards[3]
                    ])
                }
            }
            else {
                setPointsText("You do not have sufficient points to play this game! (You have " + user.points + ", you need at least " + (betAmount) + " to play.)")
            }
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
                    <button onClick={playerDrawsCard}>
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