package com.tsypk.sniper.security;

import com.tsypk.sniper.model.entity.user.User;
import com.tsypk.sniper.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tsypk on 07.02.2022 15:38
 * @project sniper
 */
@Service("securityUserService")
@Slf4j
public class JpaSecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public JpaSecurityUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        log.info("[loadUserByUsername] user with username: {} successfully loaded", username);
        return JpaSecurityUser.convertFromUser(optionalUser.get());
    }
}
