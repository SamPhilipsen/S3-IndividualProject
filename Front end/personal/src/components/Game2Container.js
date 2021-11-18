import React, {useState, useEffect} from "react";
import "./game2-styles.css"

const Game2Container = props => {
    //const [user, setUser] = useState(props.loggedInUser);
    //const [betAmount, setBetAmount] = useState();
    const [cardDeck, setCardDeck] = useState([
        {name: "card 1", value: 1},
        {name: "card 2", value: 2},
        {name: "card 3", value: 3},
        {name: "card 4", value: 4},
        {name: "card 5", value: 5},
        {name: "card 6", value: 6},
        {name: "card 7", value: 7},
        {name: "card 8", value: 8},
        {name: "card 9", value: 9},
        {name: "card 10", value: 10}
    ])
    const [playerCards, setPlayerCards] = useState([])
    const [dealerCards, setDealerCards] = useState([])
    const [isPlaying, setPlaying] = useState(false)

    /*useEffect(() => {
        setUser(props.loggedInUser)
    }, [props.loggedInUser])*/

    useEffect(() => {
        const cardsValue = getPlayerCardsValue()

        if (cardsValue > 21) {
            alert("You lost!")
        }
        if (cardsValue === 21) {
            alert("You won!")
        }
    },[playerCards])

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
        console.log("Player drew card")
        let card = drawCards(1)
        setPlayerCards(playerCards => [
            ...playerCards, card[0]
        ])
    }

    const playerStands = () => {
        console.log("Player stands")

        async function getCardsValue() {
            const cardsValue = getDealerCardsValue();
            console.log(cardsValue);
            if(cardsValue <= 16 ) {
                let card = drawCards(1)
                dealerCards.push(card[0])
                await getCardsValue()
            }
            else if (cardsValue >= 17) {
                if(cardsValue > 21) {
                    alert("You won!")
                } else {
                    const playerCardsValue = getPlayerCardsValue();
                    if(playerCardsValue > cardsValue) {
                        alert("You won!")
                    } else {
                        alert("You lost!")
                    }
                }
            }
        }
        getCardsValue()
    }

    const handleGame2Logic = e => {
        e.preventDefault()
        setPlaying(true);

        let cards = drawCards(4)
        if(cards) {
            setPlayerCards(playerCards => [
                ...playerCards, cards[0], cards[1]
            ])
            setDealerCards(dealerCards => [
                ...dealerCards, cards[2], cards[3]
            ])
        }
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
                <h1>Game 2</h1>
                <form onSubmit={handleGame2Logic} className="game2StartContainer" style={betMode}>
                    <input
                        type="text"
                        placeholder="Points you want to bet"
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
                <h2>
                    Cards left:
                    {cardDeck.length}
                </h2>
            </div>
        </div>
    )
}
export default Game2Container