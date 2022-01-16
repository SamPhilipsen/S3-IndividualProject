package very.cool.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import very.cool.application.GameLogic.Blackjack;
import very.cool.application.Model.Card;

import java.util.List;

public interface IGameRepository extends JpaRepository<Blackjack, Long> {
    /*@Modifying
    @Query("UPDATE Blackjack b SET b.cardDeck = :cardDeck, b.dealerCards = :dealerCards, b.playerCards = :playerCards WHERE b.id = :id")
    int updateBlackjackGame(@Param("id") Long id, @Param("cardDeck") List<Card> cardDeck, @Param("playerCards") List<Card> playerCards, @Param("dealerCards") List<Card> dealerCards);*/

}
