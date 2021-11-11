package very.cool.application.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import very.cool.application.Interfaces.IUserData;
import very.cool.application.Model.Member;

import java.util.List;

//UserDalJPA
@Repository
public class UserDataStorage implements IUserData {

    @Autowired
    IUserRepository repo;

    //private List<User> userList = new ArrayList<>();

    /*public UserDataStorage() {
        User Peter = new User("Peter", "123", 0, 100);
        User Johan = new User("Johan", "ABC", 1, 100);
        User Henk = new User("Henk", "000", 2, 100);

        userList.add(Peter);x
        userList.add(Johan);
        userList.add(Henk);
    }*/

    public List<Member> getMembers() { return repo.findAll(); }

    public List<Member> getMembers(String name) {
        return (List<Member>) repo.getMembersByName(name);
    }

    public List<Member> getMembers(int points) {
        return (List<Member>) repo.getMembersByPoints(points);
    }

    public Member getMember(int id) {
        return repo.getMemberById(id);
    }

    public boolean deleteMember(int id) {
        Member user = getMember(id);
        if(user != null) {
            repo.delete(user);
            return true;
        }
        else return false;
    }

    public boolean addMember(Member user) {
        repo.save(user);
        return true;
    }

    public boolean updateMember(Member user) {
        Member oldUser = this.getMember(user.getId());
        if(oldUser != null) {
           oldUser.setName(user.getName());
           oldUser.setPoints(user.getPoints());
           oldUser.setPassword(user.getPassword());
           repo.save(oldUser);
           return true;
        }
        else return false;
    }
}
