package very.cool.application.MemberClassesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.FakeDataClasses.FakeMemberDataStorage;
import very.cool.application.Service.MemberManager;
import very.cool.application.Model.Member;

class MemberManagerTest {

    MemberManager manager;
    Member testMember;
    Member wrongMember;

    @BeforeEach
    private void setup() {
        manager = new MemberManager(new FakeMemberDataStorage());
        testMember = new Member("TestMember", "00000", 0, 100);
        wrongMember = new Member("Not the test member!", "00000", 0, 100);
    }

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
        boolean result = manager.addMember(testMember);

        Assertions.assertTrue(result);
    }

    @Test
    public void addMemberTestFail()
    {
        boolean result = manager.addMember(wrongMember);

        Assertions.assertFalse(result);
    }

    @Test
    public void updateMemberTest()
    {
        boolean result = manager.updateMember(testMember);

        Assertions.assertTrue(result);
    }

    @Test
    public void updateMemberTestFail()
    {
        boolean result = manager.updateMember(wrongMember);

        Assertions.assertFalse(result);
    }
}
