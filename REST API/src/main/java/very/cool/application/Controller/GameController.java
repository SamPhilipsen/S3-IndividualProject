package very.cool.application.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Model.MemberDTO;

@RestController
@RequestMapping("/games")
public class GameController {

    @GetMapping("/cointoss")
    public ResponseEntity playCointossGame(@RequestParam(value = "side") String side) {
        Cointoss game = new Cointoss();

        game.chooseSide(side);
        if(game.flipCoin() == true) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
