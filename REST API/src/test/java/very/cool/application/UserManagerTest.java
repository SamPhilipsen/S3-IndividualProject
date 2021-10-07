package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import very.cool.application.Controller.UserController;
import very.cool.application.Logic.UserManager;
import very.cool.application.Model.User;
import very.cool.application.Repository.UserDataStorage;

public class UserManagerTest {

    @Test
    void contextLoads() {

    }

    @Test
    public void GetAllUsersTest()
    {
        UserController controller = new UserController(new FakeUserManager());

        ResponseEntity<User> result = controller.getUserPath(1);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void GetUsersFailsTest()
    {
        UserController controller = new UserController(new FakeUserManager());

        ResponseEntity<User> result = controller.getUserPath(2);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
