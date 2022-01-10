package very.cool.application.Interfaces;

import very.cool.application.GameLogic.Blackjack;

public interface IGameManager {
    boolean createBlackjackGame(Blackjack game);
    Blackjack getBlackjackGame(int id);
    boolean deleteBlackjackGame(Blackjack game);
}
