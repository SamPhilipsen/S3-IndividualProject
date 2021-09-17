package very.cool.application.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.Interfaces.iUserData;
import very.cool.application.Interfaces.iUserManager;

//@Service
public class UserManager implements iUserManager {
    private iUserData userData;

    //@Autowired
    public UserManager(iUserData userDataSource) { this.userData = userDataSource; }

}
