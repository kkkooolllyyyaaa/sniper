package com.tsypk.sniper.repository;

import com.tsypk.sniper.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author tsypk on 04.02.2022 10:52
 * @project sniper
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
