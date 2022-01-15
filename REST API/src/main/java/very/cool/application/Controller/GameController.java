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

@CrossOrigin("*")
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
    public ResponseEntity playCointossGame(@RequestBody ReceiveCointossDataRequest data) {
        Cointoss game = new Cointoss();
        Member member = memberManager.getMember(data.getUserId());

        if(member != null) {
            game.chooseSide(data.getGameData().toString());

            if(game.flipCoin() == true) {
                member.setPoints(member.getPoints() + data.getPointsBet());
            } else {
                member.setPoints(member.getPoints() - data.getPointsBet());
            }
            memberManager.updateMember(member);

            SendCointossDataRequest response = new SendCointossDataRequest(member.getPoints(), game.getCoinSide());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/blackjack")
    public ResponseEntity startGame(@RequestBody ReceiveBlackjackDataRequest data) {

        if(data == null) return ResponseEntity.notFound().build();

        Blackjack blackjack = new Blackjack(data.getBet(), data.getPlayerId());
        if(gameManager.createBlackjackGame(blackjack)) {
            return ResponseEntity.ok().body(blackjack);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/blackjack")
    public ResponseEntity updateGame(@RequestBody ReceiveBlackjackDataRequest data) {
        if(data == null) return ResponseEntity.notFound().build();
        Blackjack game = null;

        if(data.getAction().equals("hit")) {
            game = gameManager.playerDrawsCard(data.getGameId());
        }
        else if(data.getAction().equals("stand")) {
            game = gameManager.playerStands(data.getGameId());
        } else {
            return ResponseEntity.notFound().build();
        }

        SendBlackjackDataRequest dto = new SendBlackjackDataRequest(game.getId(), game.getCardDeck(), game.getDealerCards(), game.getPlayerCards(), game.getWinner());

        return ResponseEntity.ok().body(dto);

    }

}
