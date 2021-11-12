package very.cool.application;

import very.cool.application.Interfaces.IMemberData;
import very.cool.application.Model.Member;

import java.util.ArrayList;
import java.util.List;

public class FakeMemberDataStorage implements IMemberData {
    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public List<Member> getMembers(String name) {
        List<Member> members = new ArrayList<>();
        if(name == "Peter") {
            members.add(new Member());
            return members;
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
    public boolean addMember(Member member) {
        if(member.getUsername() == "TestMember") {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMember(Member member) {
        if(member.getUsername() == "TestMember") {
            return true;
        }
        return false;
    }
}
