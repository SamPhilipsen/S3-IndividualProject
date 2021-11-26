package very.cool.application.Interfaces;

import very.cool.application.Model.Member;

import java.util.List;

//IMemberDal
public interface IMemberData {
    public List<Member> getMembers();
    public List<Member> getMembers(String name);
    public List<Member> getMembers(int points);
    public Member getMember(int id);
    public boolean deleteMember(int id);
    public boolean addMember(Member member);
    public boolean updateMember(Member member);
}
