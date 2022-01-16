package very.cool.application.DTO;

import lombok.Getter;
import lombok.Setter;
import very.cool.application.Model.Card;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SendBlackjackDataRequest {
    private Long id;
    private List<Card> cardDeck = new ArrayList<>();
    private List<Card> dealerCards = new ArrayList<>();
    private List<Card> playerCards = new ArrayList<>();
    private String winner;

    public SendBlackjackDataRequest(Long id, List<Card> cardDeck, List<Card> dealerCards, List<Card> playerCards, String winner) {
        this.id = id;
        this.cardDeck = cardDeck;
        this.dealerCards = dealerCards;
        this.playerCards = playerCards;
        this.winner = winner;
    }

    public SendBlackjackDataRequest() {}
}
