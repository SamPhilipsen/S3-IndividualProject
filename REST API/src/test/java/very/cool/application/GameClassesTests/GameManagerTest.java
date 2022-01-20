package very.cool.application.GameClassesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.FakeDataClasses.FakeGameDataStorage;
import very.cool.application.FakeDataClasses.FakeMemberManager;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Service.GameManager;

public class GameManagerTest {

    GameManager manager;

    @BeforeEach
    private void setup() {
        manager = new GameManager(new FakeGameDataStorage(), new FakeMemberManager());
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(manager);
    }

    @Test
    public void playCointossTest() {
        String result = manager.playCointossGame(1, "Heads", -10);

        Assertions.assertNotNull(result);
    }

    @Test
    public void createBlackjackTest() {
        boolean result = manager.createBlackjackGame(new Blackjack(1, 1));

        Assertions.assertTrue(result);
    }

    @Test
    public void getBlackjackTest() {
        Blackjack result = manager.getBlackjackGame(1L);

        Assertions.assertNotNull(result);
    }

    @Test
    public void deleteBlackjackTest() {
        boolean result = manager.deleteBlackjackGame(new Blackjack());

        Assertions.assertTrue(result);
    }

    @Test
    public void updateBlackjackTest() {
        Blackjack result = manager.updateBlackjackGame(new Blackjack(1, 1));

        Assertions.assertNotNull(result);
    }

    @Test
    public void playerPerformsActionTest() {
        Blackjack result = manager.playerPerformsAction(1L, "hit");

        Assertions.assertNotNull(result);
    }

}
