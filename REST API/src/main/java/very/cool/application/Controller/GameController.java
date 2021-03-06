package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.DTO.ReceiveBlackjackDataRequest;
import very.cool.application.DTO.ReceiveCointossDataRequest;
import very.cool.application.DTO.SendBlackjackDataRequest;
import very.cool.application.DTO.SendCointossDataRequest;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Interfaces.IGameManager;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/games")
public class GameController {

    private IMemberManager memberManager;
    private IGameManager gameManager;

    @Autowired
    public GameController(IMemberManager memberManager, IGameManager gameManager)
    {
        this.memberManager = memberManager;
        this.gameManager = gameManager;
    }

    @PutMapping("/cointoss")
    public ResponseEntity<SendCointossDataRequest> playCointossGame(@RequestBody ReceiveCointossDataRequest data) {

        String coinSide = gameManager.playCointossGame(data.getUserId(), data.getGameData(), data.getPointsBet());

        if(coinSide == null) {
            return ResponseEntity.badRequest().body(null);
        }

        int userPoints = memberManager.getMember(data.getUserId()).getPoints();
        SendCointossDataRequest response = new SendCointossDataRequest(userPoints, coinSide);

        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/blackjack")
    public ResponseEntity<SendBlackjackDataRequest> startGame(@RequestBody ReceiveBlackjackDataRequest data) {
        int bet = data.getBet();
        int playerId = data.getPlayerId();

        Member member = memberManager.getMember(data.getPlayerId());

        if(member.deductPoints(bet)) {
            Blackjack blackjack = new Blackjack(bet, playerId);
            if(gameManager.createBlackjackGame(blackjack)) {
                SendBlackjackDataRequest dto = new SendBlackjackDataRequest(blackjack.getId(), blackjack.getCardDeck(),blackjack.getDealerCards(), blackjack.getPlayerCards(), blackjack.getWinner());
                return ResponseEntity.ok().body(dto);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/blackjack")
    public ResponseEntity<SendBlackjackDataRequest> updateGame(@RequestBody ReceiveBlackjackDataRequest data) {
        if(data == null) return ResponseEntity.notFound().build();

        Blackjack game = gameManager.playerPerformsAction(data.getGameId(), data.getAction());

        SendBlackjackDataRequest dto = new SendBlackjackDataRequest(game.getId(), game.getCardDeck(), game.getDealerCards(), game.getPlayerCards(), game.getWinner());

        return ResponseEntity.ok().body(dto);

    }

}
