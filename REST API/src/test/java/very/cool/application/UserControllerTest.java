package very.cool.application;

import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import very.cool.application.Controller.UserController;
import very.cool.application.Logic.UserManager;
import very.cool.application.Model.User;
import very.cool.application.Repository.UserDataStorage;

import java.util.List;
import java.util.Optional;

public class UserControllerTest {

    UserController controller;
    User testUser;
    User wrongUser;

    @BeforeEach
    private void setup() {
        controller = new UserController(new FakeUserManager());
        testUser = new User("TestUser", "00000", 0, 100);
        wrongUser = new User("Not the test user!", "00000", 0, 100);
    }

    @Test
    void contextLoads() {

    }

    @Test
    public void GetAllUsersTest()
    {
        ResponseEntity<User> result = controller.getUserPath(1);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void GetUsersFailsTest()
    {
        ResponseEntity<User> result = controller.getUserPath(2);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getUsersByNameTest()
    {
        Optional<String> name = Optional.of("Peter");
        ResponseEntity<List<User>> result = controller.getUsers(name);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getUserByNameFailsTest()
    {
        Optional<String> name = Optional.of("Not a real name!");
        ResponseEntity<List<User>> result = controller.getUsers(name);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteUserTest()
    {
        ResponseEntity result = controller.deletePost(1);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void createUserTest()
    {
        ResponseEntity result = controller.createUser(testUser);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void createUserTestFail()
    {
        ResponseEntity result = controller.createUser(wrongUser);

        Assertions.assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void updateUserWithObjectTest()
    {
        ResponseEntity result = controller.updateUser(testUser);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void updateUserTestWithObjectFailed()
    {
        ResponseEntity result = controller.updateUser(wrongUser);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void updateUserWithVarsTest()
    {
        String name = "TestUser";
        int id = 0;
        int points = 100;

        User user = new User(name, "00000", id, points);

        ResponseEntity result = controller.updateUser(user);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void updateUserTestWithVarsFailed()
    {
        String name = "Wrong user!";
        int id = 0;
        int points = 100;

        User user = new User(name, "00000", id, points);

        ResponseEntity result = controller.updateUser(user);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
