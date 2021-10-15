package very.cool.application.Interfaces;

import very.cool.application.Model.User;

import java.util.List;

public interface IUserManager {
    public List<User> getUsers();
    public List<User> getUsers(String name);
    public List<User> getUsers(int points);
    public User getUser(int id);
    public boolean deleteUser(int id);
    public boolean addUser(User user);
    public boolean updateUser(User user);
}
