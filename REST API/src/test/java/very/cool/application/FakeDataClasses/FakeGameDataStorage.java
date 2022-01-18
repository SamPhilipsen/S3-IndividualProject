package very.cool.application.FakeDataClasses;

import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Interfaces.IGameData;

public class FakeGameDataStorage implements IGameData {
    @Override
    public boolean createBlackjackGame(Blackjack game) {
        return true;
    }

    @Override
    public Blackjack getBlackjackGame(Long id) {
        if(id == 1L) {
            return new Blackjack(-1, 1);
        }
        return null;
    }

    @Override
    public boolean deleteBlackjackGame(Blackjack game) {
        return true;
    }

    @Override
    public Blackjack updateBlackjackGame(Blackjack game) {
        return game;
    }
}
