package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import very.cool.application.GameLogic.Blackjack;
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
        System.out.println("2" + gameId);
        Blackjack game = this.getBlackjackGame(gameId);
        System.out.println("3" + game.getId());

        if(action.equals("hit")) {
            this.playerDrawsCard(game);
            System.out.println("6" + game.getId());
        }
        else if(action.equals("stand")) {
            this.playerStands(game);
            System.out.println("7" + game.getId());
        } else {
            System.out.println("No action recorded");
            return null;
        }
        System.out.println("4" + game.getId());
        return game;
    }

    private void playerDrawsCard(Blackjack game) {
        System.out.println("5" + game.getId());
        game.drawPlayerCard();
        this.updateBlackjackGame(game);
    }

    private void playerStands(Blackjack game) {
        System.out.println("6" + game.getId());
        game.playerStands();
        this.updateBlackjackGame(game);
    }

}
