package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.Logic.UserManager;
import very.cool.application.Model.User;
import very.cool.application.Repository.UserDataStorage;

import java.util.List;

public class UserManagerTest {

    UserManager manager;
    User testUser;
    User wrongUser;

    @BeforeEach
    private void setup() {
        manager = new UserManager(new FakeUserDataStorage());
        testUser = new User("TestUser", "00000", 0, 100);
        wrongUser = new User("Not the test user!", "00000", 0, 100);
    }

    /*@Test
    private void getUsersByName()
    {
        List<User> result = manager.getUsers("Peter");

        Assertions.assertTrue(result instanceof User);
    }*/

    @Test
    public void getUserTest()
    {
        User result = manager.getUser(1);

        Assertions.assertTrue(result instanceof User);
    }

    @Test
    public void getUserTestFail()
    {
        User result = manager.getUser(2);

        Assertions.assertFalse(result instanceof User);
    }

    @Test
    public void deleteUserTest()
    {
        boolean result = manager.deleteUser(1);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteUserTestFail()
    {
        boolean result = manager.deleteUser(2);

        Assertions.assertFalse(result);
    }

    @Test
    public void addUserTest()
    {
        boolean result = manager.addUser(testUser);

        Assertions.assertTrue(result);
    }

    @Test
    public void addUserTestFail()
    {
        boolean result = manager.addUser(wrongUser);

        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserTest()
    {
        boolean result = manager.updateUser(testUser);

        Assertions.assertTrue(result);
    }

    @Test
    public void updateUserTestFail()
    {
        boolean result = manager.updateUser(wrongUser);

        Assertions.assertFalse(result);
    }
}
