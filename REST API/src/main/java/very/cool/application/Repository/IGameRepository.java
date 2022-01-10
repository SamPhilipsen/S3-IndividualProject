package very.cool.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import very.cool.application.GameLogic.Blackjack;

public interface IGameRepository extends JpaRepository<Blackjack, Long> {

}
