package very.cool.application.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import very.cool.application.Repository.IUserRepository;

import java.util.Collections;

public class AuthenticationUserDetailService implements UserDetailsService {

    private final IUserRepository userService;

    public AuthenticationUserDetailService(IUserRepository userRepository) {
        userService = userRepository;
    }


    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userService.getUsersByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
