package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.Service.UserManager;
import very.cool.application.Model.Member;

public class UserManagerTest {

    UserManager manager;
    Member testUser;
    Member wrongUser;

    @BeforeEach
    private void setup() {
        manager = new UserManager(new FakeUserDataStorage());
        testUser = new Member("TestUser", "00000", 0, 100);
        wrongUser = new Member("Not the test user!", "00000", 0, 100);
    }

    /*@Test
    private void getMembersByName()
    {
        List<User> result = manager.getMembers("Peter");

        Assertions.assertTrue(result instanceof User);
    }*/

    @Test
    public void getMemberTest()
    {
        Member result = manager.getMember(1);

        Assertions.assertTrue(result instanceof Member);
    }

    @Test
    public void getMemberTestFail()
    {
        Member result = manager.getMember(2);

        Assertions.assertFalse(result instanceof Member);
    }

    @Test
    public void deleteMemberTest()
    {
        boolean result = manager.deleteMember(1);

        Assertions.assertTrue(result);
    }

    @Test
    public void deleteMemberTestFail()
    {
        boolean result = manager.deleteMember(2);

        Assertions.assertFalse(result);
    }

    @Test
    public void addMemberTest()
    {
        boolean result = manager.addMember(testUser);

        Assertions.assertTrue(result);
    }

    @Test
    public void addMemberTestFail()
    {
        boolean result = manager.addMember(wrongUser);

        Assertions.assertFalse(result);
    }

    @Test
    public void updateMemberTest()
    {
        boolean result = manager.updateMember(testUser);

        Assertions.assertTrue(result);
    }

    @Test
    public void updateMemberTestFail()
    {
        boolean result = manager.updateMember(wrongUser);

        Assertions.assertFalse(result);
    }
}
