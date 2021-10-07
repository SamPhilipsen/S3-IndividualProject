package very.cool.application.Repository;

import org.springframework.stereotype.Repository;
import very.cool.application.Interfaces.IUserData;
import very.cool.application.Model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDataStorage implements IUserData {

    private List<User> userList = new ArrayList<>();

    public UserDataStorage() {
        User Peter = new User("Peter", "123", 0, 100);
        User Johan = new User("Johan", "ABC", 1, 100);
        User Henk = new User("Henk", "000", 2, 100);

        userList.add(Peter);
        userList.add(Johan);
        userList.add(Henk);
    }

    public List<User> getUsers() { return userList; }

    public List<User> getUsers(String name) {
        List<User> filteredList = new ArrayList<>();
        for(User user : userList) {
            if(user.getName().equals(name)) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    public List<User> getUsers(int points) {
        List<User> filteredList = new ArrayList<>();
        for(User user : userList) {
            if(user.getPoints() == points) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    public User getUser(int id) {
        for(User user : userList) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(int id) {
        User user = getUser(id);
        if(user != null) {
            userList.remove(user);
            return true;
        }
        else return false;
    }

    public boolean addUser(User user) {
        if(this.getUser(user.getId()) == null) {
            userList.add(user);
            return true;
        }
        else return false;
    }

    public boolean updateUser(User user) {
        User oldUser = this.getUser(user.getId());
        if(oldUser != null) {
           oldUser.setName(user.getName());
           oldUser.setPoints(user.getPoints());
           return true;
        }
        else return false;
    }
}
