package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Interfaces.IGameData;
import very.cool.application.Interfaces.IGameManager;

@Service
public class GameManager implements IGameManager {

    private IGameData fakeData;

    @Autowired
    public GameManager(IGameData gameData) {
        this.fakeData = gameData;
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
        return fakeData.updateBlackjackGame(game);
    }

    public Blackjack playerDrawsCard(Long id) {
        Blackjack game = this.getBlackjackGame(id);
        game.drawPlayerCard();
        return this.updateBlackjackGame(game);
    }

    public Blackjack playerStands(Long id) {
        Blackjack game = this.getBlackjackGame(id);
        game.playerStands();
        return this.updateBlackjackGame(game);
    }

}
