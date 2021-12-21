package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.DTO.ReceiveGameDataRequest;
import very.cool.application.DTO.MemberDTO;
import very.cool.application.DTO.SendGameDataRequest;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

@CrossOrigin("*")
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private IMemberManager memberManager;

    public GameController(IMemberManager memberManager)
    {
        this.memberManager = memberManager;
    }

    @PutMapping("/cointoss")
    public ResponseEntity playCointossGame(@RequestBody ReceiveGameDataRequest data) {
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

            SendGameDataRequest response = new SendGameDataRequest(member.getPoints(), game.getCoinSide());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }
}
