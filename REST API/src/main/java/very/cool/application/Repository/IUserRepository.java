package very.cool.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import very.cool.application.Model.User;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> getUsersByName(String name);
    List<User> getUsersByPoints(int points);
    User getUserById(int id);
}
