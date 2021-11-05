package very.cool.application;

import very.cool.application.Interfaces.IUserData;
import very.cool.application.Model.User;

import java.util.ArrayList;
import java.util.List;

public class FakeUserDataStorage implements IUserData {
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public List<User> getUsers(String name) {
        List<User> users = new ArrayList<>();
        if(name == "Peter") {
            users.add(new User());
            return users;
        }
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
        if(id == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        if(user.getName() == "TestUser") {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if(user.getName() == "TestUser") {
            return true;
        }
        return false;
    }
}
