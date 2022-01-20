package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import very.cool.application.DTO.SendCointossDataRequest;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Interfaces.IGameData;
import very.cool.application.Interfaces.IGameManager;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

@Service
public class GameManager implements IGameManager {

    private IGameData fakeData;
    private IMemberManager memberManager;

    @Autowired
    public GameManager(IGameData gameData, IMemberManager memberManager) {
        this.fakeData = gameData;
        this.memberManager = memberManager;
    }

    public String playCointossGame(int userId, Object gameData, int bet) {
        Cointoss game = new Cointoss();
        Member member = memberManager.getMember(userId);

        if(member != null) {
            if(member.deductPoints(bet)) {
                game.chooseSide(gameData.toString());

                if(game.flipCoin()) {
                    member.setPoints(member.getPoints() + bet*2);
                }
                memberManager.updateMember(member);
                return game.getCoinSide();
            }
        }
        return null;
    }

    @Override
    public boolean createBlackjackGame(Blackjack game) {
        game.startGame();
        return fakeData.createBlackjackGame(game);
    }

    @Override
    public Blackjack getBlackjackGame(Long id) {
        return fakeData.getBlackjackGame(id);
    }

    @Override
    public boolean deleteBlackjackGame(Blackjack game) {
        return fakeData.deleteBlackjackGame(game);
    }

    public Blackjack updateBlackjackGame(Blackjack game) {

        Member player = memberManager.getMember(game.getPlayerId());

        if(game.getWinner().equals("player")) {
            player.addPoints(game.getBet()*2);
        }

        memberManager.updateMember(player);

        return fakeData.updateBlackjackGame(game);
    }

    public Blackjack playerPerformsAction(Long gameId, String action) {
        Blackjack game = this.getBlackjackGame(gameId);

        if(action.equals("hit")) {
            this.playerDrawsCard(game);
        }
        else if(action.equals("stand")) {
            this.playerStands(game);
        } else {
            return null;
        }
        return game;
    }

    private void playerDrawsCard(Blackjack game) {
        game.drawPlayerCard();
        this.updateBlackjackGame(game);
    }

    private void playerStands(Blackjack game) {
        game.playerStands();
        this.updateBlackjackGame(game);
    }

}
