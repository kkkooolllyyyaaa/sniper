package com.tsypk.sniper.service.impls;

import com.tsypk.sniper.model.entity.user.Role;
import com.tsypk.sniper.model.entity.user.Status;
import com.tsypk.sniper.model.entity.user.User;
import com.tsypk.sniper.repository.UserRepository;
import com.tsypk.sniper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tsypk on 07.02.2022 10:42
 * @project sniper
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);

        log.info("[register]  user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("[getAll] total {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            log.warn("[finByUsername] not any user found by username: {}", username);
            return null;
        }
        log.info("[findByUsername] user: {} found by username: {}", optionalUser.get(), username);
        return optionalUser.get();
    }

    @Override
    public boolean userExistByName(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("[findById] no user found by id: {}", id);
            return null;
        }
        log.info("[findByUsername] user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("[delete] user with id: {} successfully deleted", id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        userRepository.deleteAllInBatch();
    }
}
