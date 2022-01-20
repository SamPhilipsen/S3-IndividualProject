package very.cool.application.Interfaces;

import very.cool.application.GameLogic.Blackjack;

public interface IGameData {
    boolean createBlackjackGame(Blackjack game);
    Blackjack getBlackjackGame(Long id);
    boolean deleteBlackjackGame(Blackjack game);
    Blackjack updateBlackjackGame(Blackjack game);
}
