package very.cool.application.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Interfaces.IGameData;

@Repository
public class GameDataStorage implements IGameData {

    private IGameRepository repo;

    @Autowired
    public GameDataStorage(IGameRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean createBlackjackGame(Blackjack game) {
        repo.save(game);
        return true;
    }

    @Override
    public Blackjack getBlackjackGame(Long id) {
        return repo.getOne(id);
    }

    @Override
    public boolean deleteBlackjackGame(Blackjack game) {
        try {
            repo.delete(game);
        } catch(Exception ex) {
            return false;
        }
        return true;
    }

    public Blackjack updateBlackjackGame(Blackjack game) {
        Blackjack oldGame = this.getBlackjackGame(game.getId());
        if(oldGame != null) {
            oldGame.setCardDeck(game.getCardDeck());
            oldGame.setDealerCards(game.getDealerCards());
            oldGame.setPlayerCards(game.getPlayerCards());
            repo.save(oldGame);
            return oldGame;
        }
        return null;
    }
}
