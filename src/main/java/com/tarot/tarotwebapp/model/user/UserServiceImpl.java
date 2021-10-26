package com.tarot.tarotwebapp.model.user;

import com.tarot.tarotwebapp.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tarot.tarotwebapp.enumeration.Role.*;

@Service
@Qualifier("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(username + " can not found on database"));
        userRepo.save(user);
        return new UserPrinciple(user);
    }

    @Override
    public User addNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        return userRepo.save(user);
    }
}
