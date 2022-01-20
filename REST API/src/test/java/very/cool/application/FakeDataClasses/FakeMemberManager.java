package very.cool.application.FakeDataClasses;

import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

import java.util.ArrayList;
import java.util.List;

public class FakeMemberManager implements IMemberManager {
    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public List<Member> getMembers(String name) {
        List<Member> users = new ArrayList<>();
        if(name == "Peter") {
            users.add(new Member("Peter", "123", 0, 100));
            return users;
        }
        return null;
    }

    @Override
    public List<Member> getMembers(int points) {
        return null;
    }

    @Override
    public Member getMember(int id) {
        if(id == 1) {
            return new Member();
        }
        return null;
    }

    @Override
    public boolean deleteMember(int id) {
        if(id == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addMember(Member user) {
        if(user.getUsername() == "TestUser") {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(Member user) {
        if(user.getUsername() == "TestUser") {
            return true;
        }
        return false;
    }
}
