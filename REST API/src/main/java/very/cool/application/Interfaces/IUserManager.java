package very.cool.application.Interfaces;

import very.cool.application.Model.Member;

import java.util.List;

public interface IUserManager {
    public List<Member> getMembers();
    public List<Member> getMembers(String name);
    public List<Member> getMembers(int points);
    public Member getMember(int id);
    public boolean deleteMember(int id);
    public boolean addMember(Member user);
    public boolean updateMember(Member user);
}
