package very.cool.application.Repository;

import org.springframework.stereotype.Repository;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Interfaces.IGameData;

@Repository
public class GameDataStorage implements IGameData {

    IGameRepository repo;

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
}
