package very.cool.application.GameLogic;

import org.hibernate.annotations.GenericGenerator;
import very.cool.application.Model.Card;
import very.cool.application.Model.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "game")
public class Blackjack {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator= "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @Column(name = "bet")
    private int bet;

    @Column(name = "playerId")
    private int playerId;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "cardDeck")
    private List<Card> cardDeck;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "dealerCards")
    private List<Card> dealerCards;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "playerCards")
    private List<Card> playerCards;
    private String winner;

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public List<Card> getCardDeck() { return this.cardDeck; }
    public void setCardDeck(List<Card> deck) { this.cardDeck = deck; }
    public List<Card> getDealerCards() { return this.dealerCards; }
    public void setDealerCards(List<Card> deck) { this.dealerCards = deck; }
    public List<Card> getPlayerCards() { return this.playerCards; }
    public void setPlayerCards(List<Card> deck) { this.playerCards = deck; }
    public String getWinner() { return this.winner; }
    public int getPlayerId() { return this.playerId; }
    public int getBet() { return this.bet; }


    public Blackjack(int bet, int playerId) {

        this.playerId = playerId;
        this.bet = bet;
        this.winner = "";
        this.playerCards = new ArrayList<>();
        this.dealerCards = new ArrayList<>();
        this.cardDeck = new ArrayList<>();
        cardDeck.add(new Card("hearts 2", 2));
        cardDeck.add(new Card("hearts 3", 3));
        cardDeck.add(new Card("hearts 4", 4));
        cardDeck.add(new Card("hearts 5", 5));
        cardDeck.add(new Card("hearts 6", 6));
        cardDeck.add(new Card("hearts 7", 7));
        cardDeck.add(new Card("hearts 8", 8));
        cardDeck.add(new Card("hearts 9", 9));
        cardDeck.add(new Card("hearts 10", 10));
        cardDeck.add(new Card("hearts jack", 10));
        cardDeck.add(new Card("hearts queen", 10));
        cardDeck.add(new Card("hearts king", 10));
        cardDeck.add(new Card("spades 2", 2));
        cardDeck.add(new Card("spades 3", 3));
        cardDeck.add(new Card("spades 4", 4));
        cardDeck.add(new Card("spades 5", 5));
        cardDeck.add(new Card("spades 6", 6));
        cardDeck.add(new Card("spades 7", 7));
        cardDeck.add(new Card("spades 8", 8));
        cardDeck.add(new Card("spades 9", 9));
        cardDeck.add(new Card("spades 10", 10));
        cardDeck.add(new Card("spades jack", 10));
        cardDeck.add(new Card("spades queen", 10));
        cardDeck.add(new Card("spades king", 10));
        cardDeck.add(new Card("diamonds 2", 2));
        cardDeck.add(new Card("diamonds 3", 3));
        cardDeck.add(new Card("diamonds 4", 4));
        cardDeck.add(new Card("diamonds 5", 5));
        cardDeck.add(new Card("diamonds 6", 6));
        cardDeck.add(new Card("diamonds 7", 7));
        cardDeck.add(new Card("diamonds 8", 8));
        cardDeck.add(new Card("diamonds 9", 9));
        cardDeck.add(new Card("diamonds 10", 10));
        cardDeck.add(new Card("diamonds jack", 10));
        cardDeck.add(new Card("diamonds queen", 10));
        cardDeck.add(new Card("diamonds king", 10));
        cardDeck.add(new Card("clubs 2", 2));
        cardDeck.add(new Card("clubs 3", 3));
        cardDeck.add(new Card("clubs 4", 4));
        cardDeck.add(new Card("clubs 5", 5));
        cardDeck.add(new Card("clubs 6", 6));
        cardDeck.add(new Card("clubs 7", 7));
        cardDeck.add(new Card("clubs 8", 8));
        cardDeck.add(new Card("clubs 9", 9));
        cardDeck.add(new Card("clubs 10", 10));
        cardDeck.add(new Card("clubs jack", 10));
        cardDeck.add(new Card("clubs queen", 10));
        cardDeck.add(new Card("clubs king", 10));
    }

    public Blackjack(Long id, int bet, int playerId, List<Card> cardDeck, List<Card> dealerCards, List<Card> playerCards, String winner) {
        this.id = id;
        this.bet = bet;
        this.playerId = playerId;
        this.cardDeck = cardDeck;
        this.dealerCards = dealerCards;
        this.playerCards = playerCards;
        this.winner = winner;
    }

    public Blackjack() {}

    private int getDeckValue(List<Card> cardDeck) {
        int cardsValue = 0;
        for(Card c : cardDeck) {
            cardsValue += c.getValue();
        }
        return cardsValue;
    }

    private void checkIfHigherThan21() {
        int dealerCardsValue = getDeckValue(dealerCards);
        int playerCardsValue = getDeckValue(playerCards);

        if(dealerCardsValue > 21) {
            this.winner = "player";
        } else if (playerCardsValue > 21) {
            this.winner = "dealer";
        }
    }

    private void compareCardsValues() {
        int dealerCardsValue = getDeckValue(dealerCards);
        int playerCardsValue = getDeckValue(playerCards);

        if(dealerCardsValue > playerCardsValue) {
            this.winner = "dealer";
        } else if (playerCardsValue > dealerCardsValue) {
            this.winner = "player";
        } else {
            this.winner = "dealer";
        }
    }

    public Card drawCard() {
        if(cardDeck.size() <= 0) return null;

        Random random = new Random();

        Card randomCard = cardDeck.get(random.nextInt(cardDeck.size()));
        cardDeck.remove(randomCard);

        return randomCard;
    }

    public void startGame() {
        Collections.addAll(dealerCards, drawCard(), drawCard());
        Collections.addAll(playerCards, drawCard(), drawCard());
    }

    public void drawPlayerCard() {
        Card card = this.drawCard();
        this.playerCards.add(card);
        checkIfHigherThan21();
    }

    public void playerStands() {
        while(getDeckValue(dealerCards) < 17 && this.winner.equals("")){
            dealerCards.add(drawCard());
        }
        compareCardsValues();
        checkIfHigherThan21();
    }
}
