package very.cool.application.GameClassesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import very.cool.application.Controller.GameController;
import very.cool.application.DTO.ReceiveBlackjackDataRequest;
import very.cool.application.DTO.ReceiveCointossDataRequest;
import very.cool.application.DTO.SendCointossDataRequest;
import very.cool.application.FakeDataClasses.FakeGameManager;
import very.cool.application.FakeDataClasses.FakeMemberManager;

public class GameControllerTest {

    GameController controller;

    @BeforeEach
    private void setup() {
        controller = new GameController(new FakeMemberManager(), new FakeGameManager());
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(controller);
    }

    @Test
    public void cointossTest() {
        ReceiveCointossDataRequest data = new ReceiveCointossDataRequest(1, "heads", 10);
        ResponseEntity<SendCointossDataRequest> result = controller.playCointossGame(data);

        Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    public void blackjackStartWithEnoughPointsTest() {
        ReceiveBlackjackDataRequest data = new ReceiveBlackjackDataRequest(1, -10);
        ResponseEntity result = controller.startGame(data);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void blackjackStartWithoutEnoughPointsTest() {
        ReceiveBlackjackDataRequest data = new ReceiveBlackjackDataRequest(1, 10);
        ResponseEntity result = controller.startGame(data);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void blackjackHitActionTest() {
        ReceiveBlackjackDataRequest data = new ReceiveBlackjackDataRequest(1L, "hit");
        ResponseEntity result = controller.updateGame(data);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
