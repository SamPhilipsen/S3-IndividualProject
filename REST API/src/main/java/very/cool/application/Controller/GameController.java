package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.DTO.ReceiveCointossDataRequest;
import very.cool.application.DTO.SendCointossDataRequest;
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
}
