import React, {useState} from "react";

const BettingComponent = props => {
    const [betAmount, setBetAmount] = useState();

    const onBetChange = e => {
        var amount = parseInt(e.target.value)
        if (!isNaN(amount)) {
            setBetAmount(parseInt(amount))
        } else {
            setBetAmount("");
        }
        props.defineBet(amount);
    }

    return (
        <input
            type="text"
            className="bet-input"
            placeholder="Points you want to bet"
            value={betAmount}
            name="bet-points"
            onChange={onBetChange}
        />
    )
}

export default BettingComponent