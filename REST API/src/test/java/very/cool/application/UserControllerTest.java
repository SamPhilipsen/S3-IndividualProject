package very.cool.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import very.cool.application.Controller.UserController;
import very.cool.application.Model.Member;

import java.util.List;
import java.util.Optional;

public class UserControllerTest {

    UserController controller;
    Member testUser;
    Member wrongUser;

    @BeforeEach
    private void setup() {
        controller = new UserController(new FakeUserManager());
        testUser = new Member("TestUser", "00000", 0, 100);
        wrongUser = new Member("Not the test user!", "00000", 0, 100);
    }

    @Test
    void contextLoads() {

    }

    @Test
    public void GetAllUsersTest()
    {
        ResponseEntity<Member> result = controller.getMemberPath(1);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getMembersFailsTest()
    {
        ResponseEntity<Member> result = controller.getMemberPath(2);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getMembersByNameTest()
    {
        Optional<String> name = Optional.of("Peter");
        ResponseEntity<List<Member>> result = controller.getMembers(name);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getMemberByNameFailsTest()
    {
        Optional<String> name = Optional.of("Not a real name!");
        ResponseEntity<List<Member>> result = controller.getMembers(name);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteMemberTest()
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
    void updateMemberWithObjectTest()
    {
        ResponseEntity result = controller.updateMember(testUser);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void updateMemberTestWithObjectFailed()
    {
        ResponseEntity result = controller.updateMember(wrongUser);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void updateMemberWithVarsTest()
    {
        String name = "TestUser";
        int id = 0;
        int points = 100;

        Member user = new Member(name, "00000", id, points);

        ResponseEntity result = controller.updateMember(user);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void updateMemberTestWithVarsFailed()
    {
        String name = "Wrong user!";
        int id = 0;
        int points = 100;

        Member user = new Member(name, "00000", id, points);

        ResponseEntity result = controller.updateMember(user);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
