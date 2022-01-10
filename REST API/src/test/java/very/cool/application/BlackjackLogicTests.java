package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Model.Card;

public class BlackjackLogicTests {
    Blackjack game;

    @BeforeEach
    public void setup() {
        game = new Blackjack();
    }

    @Test
    public void startGameTest() {
        game.startGame();

        Assertions.assertFalse(game.getDealerCards().isEmpty());
        Assertions.assertFalse(game.getPlayerCards().isEmpty());

        //Card deck starts as being 48 cards big, so if the game has started and cards have been drawn, it should be less than 48.
        Assertions.assertTrue((game.getCardDeck().size() < 48));
    }

    @Test
    public void drawCardTest() {
        Card card = game.drawCard();

        Assertions.assertNotNull(card);
    }

    @Test
    public void drawCardWhenDeckIsEmptyTest() {
        int deckSize = game.getCardDeck().size();
        for(int i = 0; i < deckSize; i++) {
            //This is to set the carddeck to 0 without having a public setter in the blackjack class
            game.drawCard();
        }
        Assertions.assertNull(game.drawCard());
    }

    @Test
    public void StandTest() {

    }

}
