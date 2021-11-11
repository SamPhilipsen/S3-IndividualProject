package very.cool.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import very.cool.application.Model.Member;

import java.util.List;

public interface IUserRepository extends JpaRepository<Member, Long> {
    List<Member> getMembersByName(String name);
    List<Member> getMembersByPoints(int points);
    Member getMemberById(int id);
}
