package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import very.cool.application.Model.User;

@SpringBootTest
public class MainApplicationTests {

    User testUser = new User("test", "000", -1, 100);
    @Test
    void contextLoads() {

    }

    @Test
    public void deductPointsDeductsPoints() {
        testUser.setPoints(100);
        testUser.deductPoints(50);

        Assertions.assertEquals(50, testUser.getPoints());
    }

    @Test
    public void deductingPointsReturnsFalseIfNoPoints() {
        testUser.setPoints(100);

        Assertions.assertFalse(testUser.deductPoints(150));
    }

    @Test
    public void addingPointsAddsPoints() {
        testUser.setPoints(0);
        testUser.addPoints(50);

        Assertions.assertEquals(50, testUser.getPoints());
    }

    @Test
    public void addingPointsDoesntAddIfPointsAreZero() {
        testUser.setPoints(100);
        testUser.addPoints(-1);

        Assertions.assertEquals(100, testUser.getPoints());
    }
}
