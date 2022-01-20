package very.cool.application.FakeDataClasses;

import very.cool.application.GameLogic.Blackjack;
import very.cool.application.GameLogic.Cointoss;
import very.cool.application.Interfaces.IGameManager;

public class FakeGameManager implements IGameManager {
    @Override
    public String playCointossGame(int userId, Object gameData, int bet) {
        return "Heads";
    }

    @Override
    public boolean createBlackjackGame(Blackjack game) {
        return true;
    }

    @Override
    public Blackjack getBlackjackGame(Long id) {
        return null;
    }

    @Override
    public boolean deleteBlackjackGame(Blackjack game) {
        return false;
    }

    @Override
    public Blackjack updateBlackjackGame(Blackjack game) {
        return null;
    }

    @Override
    public Blackjack playerPerformsAction(Long gameId, String action) {
        return new Blackjack(1, 1);
    }
}
