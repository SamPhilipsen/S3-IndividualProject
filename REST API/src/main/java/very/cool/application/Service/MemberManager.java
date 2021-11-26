package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.Interfaces.IMemberData;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

import java.util.List;

//Service class
@Service
public class MemberManager implements IMemberManager {

    private IMemberData fakeData;

    @Autowired
    public MemberManager(IMemberData memberData)
    {
        fakeData = memberData;
    }

    public List<Member> getMembers()
    {
        return this.fakeData.getMembers();
    }

    public List<Member> getMembers(String name)
    {
        return this.fakeData.getMembers(name);
    }

    public List<Member> getMembers(int points)
    {
        return this.fakeData.getMembers(points);
    }

    public Member getMember(int id)
    {
        return this.fakeData.getMember(id);
    }

    public boolean deleteMember(int id)
    {
        return this.fakeData.deleteMember(id);
    }

    public boolean addMember(Member user)
    {
        return this.fakeData.addMember(user);
    }

    public boolean updateMember(Member user) {
        return this.fakeData.updateMember(user);
    }
}
