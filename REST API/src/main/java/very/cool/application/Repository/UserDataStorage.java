package very.cool.application.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import very.cool.application.Interfaces.IUserData;
import very.cool.application.Model.User;

import java.util.List;
import java.util.Optional;

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

    public List<User> getUsers() { return repo.findAll(); }

    public List<User> getUsers(String name) {
        return (List<User>) repo.getUsersByName(name);
    }

    public List<User> getUsers(int points) {
        return (List<User>) repo.getUsersByPoints(points);
    }

    public User getUser(int id) {
        return repo.getUserById(id);
    }

    public boolean deleteUser(int id) {
        User user = getUser(id);
        if(user != null) {
            repo.delete(user);
            return true;
        }
        else return false;
    }

    public boolean addUser(User user) {
        repo.save(user);
        return true;
    }

    public boolean updateUser(User user) {
        User oldUser = this.getUser(user.getId());
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
