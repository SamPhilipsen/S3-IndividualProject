import React, {useState, useEffect} from "react";
import "./game2-styles.css"

const Game2Container = props => {
    const [user, setUser] = useState(props.loggedInUser);
    const [betAmount, setBetAmount] = useState();
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


    console.log(cardDeck);

    useEffect(() => {
        setUser(props.loggedInUser)
    }, [props.loggedInUser])

    const drawCards = (cardAmount) => {

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

        console.log(results)

        let newDeck = cardDeck.filter((card) => {
            if(!results.includes(card)) { return card; }
        })


        if(results != null) {
            console.log(newDeck)
            setCardDeck(newDeck)
            return results
        }

        /*setCardDeck(cardDeck => [
                ...cardDeck.filter(card => {
                    return card !== result
                })
            ])*/
    }

    const handleGame2Logic = e => {
        e.preventDefault()
        let cards = drawCards(4)

        playerCards.push(cards[0], cards[1])
        dealerCards.push(cards[2], cards[3])
        //setPlayerCards([...playerCards, drawCard()])
       // setDealerCards([...dealerCards, drawCard()])

    }

    return (
        <div className="gameBaseContainer">
            <h1>Game 2</h1>
            <form onSubmit={handleGame2Logic} className="game2container">
                <input type="text"
                    placeholder="Points you want to bet"
                />

                <button input="submit" className="game-submit">
                    Draw cards
                </button>
                <div className="gameStartContainer">
                    <h2>Your cards:</h2>
                    {playerCards.map(card => {
                        return <h3 key={card.name}>{card.name}</h3>
                    })}

                    <h2>Dealer cards:</h2>
                    {dealerCards.map(card => {
                        return <h3 key={card.name}>{card.name}</h3>
                    })}
                    <h2>
                        Cards left:
                        {cardDeck.length}
                    </h2>
                </div>
            </form>
        </div>
    )
}
export default Game2Container