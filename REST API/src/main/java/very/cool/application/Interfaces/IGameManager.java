package very.cool.application.Interfaces;

import very.cool.application.GameLogic.Blackjack;

public interface IGameManager {
    String playCointossGame(int userId, Object gameData, int bet);
    boolean createBlackjackGame(Blackjack game);
    Blackjack getBlackjackGame(Long id);
    boolean deleteBlackjackGame(Blackjack game);
    Blackjack updateBlackjackGame(Blackjack game);
    Blackjack playerPerformsAction(Long gameId, String action);
}
