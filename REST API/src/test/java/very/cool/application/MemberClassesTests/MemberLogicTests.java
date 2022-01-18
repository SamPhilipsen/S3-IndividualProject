package very.cool.application.MemberClassesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import very.cool.application.Model.Member;

public class MemberLogicTests {

    @Test
    public void deductPointsTest() {
        Member member = new Member("", "", 0, 100);
        boolean result = member.deductPoints(10);

        Assertions.assertTrue(result);
    }

    @Test
    public void deductTooManyPointsTest() {
        Member member = new Member("", "", 0, 100);
        boolean result = member.deductPoints(1000);

        Assertions.assertFalse(result);
    }

    @Test
    public void addingPointsTest() {
        Member member = new Member("", "", 0, 100);
        member.addPoints(50);

        Assertions.assertEquals(150, member.getPoints());
    }
}
