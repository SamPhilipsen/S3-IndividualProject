package very.cool.application;

import very.cool.application.Interfaces.IUserManager;
import very.cool.application.Model.User;

import java.util.List;

public class FakeUserManager implements IUserManager {
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<User> getUsers(String name) {
        return null;
    }

    @Override
    public List<User> getUsers(int points) {
        return null;
    }

    @Override
    public User getUser(int id) {
        if(id == 1) {
            return new User();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
