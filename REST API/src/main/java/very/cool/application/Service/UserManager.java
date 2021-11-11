package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.Interfaces.IUserData;
import very.cool.application.Interfaces.IUserManager;
import very.cool.application.Model.Member;

import java.util.List;

//Service class
@Service
public class UserManager implements IUserManager {

    private IUserData fakeData;

    @Autowired
    public UserManager(IUserData userData)
    {
        fakeData = userData;
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
