package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.Model.Member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import very.cool.application.Repository.IUserRepository;

import java.util.Collections;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {

    private final IUserRepository userService;

    @Autowired
    public AuthenticationUserDetailService(IUserRepository userRepository) {
        userService = userRepository;
    }


    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = (Member) userService.getMembersByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.emptyList());
    }
}
