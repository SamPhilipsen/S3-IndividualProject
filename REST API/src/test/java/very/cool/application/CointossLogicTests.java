package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.GameLogic.Cointoss;

public class CointossLogicTests {

    Cointoss game;

    @BeforeEach
    public void setup() {
        game = new Cointoss();
    }

    @Test
    public void ChooseCoinsideTest() {
        game.chooseSide("Heads");
        Assertions.assertEquals("Heads", game.getChosenSide());
    }

    @Test
    public void CoinflipTest() {
        game.chooseSide("Heads");
        game.flipCoin();
        String coinSide = game.getCoinSide();
        boolean result = false;

        if(coinSide == "Heads" || coinSide == "Tails") {
            result = true;
        }

        Assertions.assertTrue(result);

    }
}
