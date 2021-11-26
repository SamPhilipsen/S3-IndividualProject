package very.cool.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import very.cool.application.Model.Member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import very.cool.application.Repository.IMemberRepository;
import very.cool.application.Repository.MemberDataStorage;

import java.util.Collections;
import java.util.List;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {

    private final MemberDataStorage userService;

    @Autowired
    public AuthenticationUserDetailService(MemberDataStorage userRepository) {
        userService = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userService.getMembers(username).get(0);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(member.getUsername(), member.getPassword(), Collections.emptyList());
    }
}
