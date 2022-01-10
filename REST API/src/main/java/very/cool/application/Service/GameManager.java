package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Interfaces.IGameData;
import very.cool.application.Interfaces.IGameManager;

@Service
public class GameManager implements IGameManager {

    IGameData fakeData;

    @Autowired
    public GameManager(IGameData gameData) {
        this.fakeData = gameData;
    }

    @Override
    public boolean createBlackjackGame(Blackjack game) {
        return fakeData.createBlackjackGame(game);
    }

    @Override
    public Blackjack getBlackjackGame(int id) {
        return null;
    }

    @Override
    public boolean deleteBlackjackGame(Blackjack game) {
        return fakeData.deleteBlackjackGame(game);
    }
}
