package very.cool.application.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveBlackjackDataRequest {
    private Long gameId;
    private String action;
    private int bet;
    private int playerId;

    public ReceiveBlackjackDataRequest(int playerId, int bet) {
        this.playerId = playerId;
        this.bet = bet;
    }

    public ReceiveBlackjackDataRequest(Long id, String action) {
        this.gameId = id;
        this.action = action;
    }

    public ReceiveBlackjackDataRequest() {}
}
