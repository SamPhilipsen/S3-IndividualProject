package very.cool.application.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import very.cool.application.Interfaces.IUserData;
import very.cool.application.Interfaces.IUserManager;
import very.cool.application.Model.User;

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

    public List<User> getUsers()
    {
        return this.fakeData.getUsers();
    }

    public List<User> getUsers(String name)
    {
        return this.fakeData.getUsers(name);
    }

    public List<User> getUsers(int points)
    {
        return this.fakeData.getUsers(points);
    }

    public User getUser(int id)
    {
        return this.fakeData.getUser(id);
    }

    public boolean deleteUser(int id)
    {
        return this.fakeData.deleteUser(id);
    }

    public boolean addUser(User user)
    {
        return this.fakeData.addUser(user);
    }

    public boolean updateUser(User user) {
        return this.fakeData.updateUser(user);
    }
}
