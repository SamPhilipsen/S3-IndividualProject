package very.cool.application.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Model.MemberDTO;

@RestController
@RequestMapping("/games")
public class GameController {

    private Cointoss game = new Cointoss();

    @GetMapping("/cointoss/pick-side")
    public ResponseEntity pickCoinside(@RequestParam(value = "side") String side) {
        game.chooseSide(side);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cointoss/play")
    public ResponseEntity playCointossGame() {

        if(game.flipCoin() == true) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
