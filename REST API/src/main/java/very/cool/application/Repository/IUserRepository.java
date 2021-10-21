package very.cool.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import very.cool.application.Model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User getUsersByName(String name);
    User getUsersByPoints(int points);
    User getUserById(int id);
}
