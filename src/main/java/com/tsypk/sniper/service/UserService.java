package com.tsypk.sniper.service;

import com.tsypk.sniper.model.entity.user.User;

import java.util.List;

/**
 * @author tsypk on 07.02.2022 10:42
 * @project sniper
 */
public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String userName);

    boolean userExistByName(String username);

    User findById(Long id);

    void delete(Long id);

    void deleteAll();
}
