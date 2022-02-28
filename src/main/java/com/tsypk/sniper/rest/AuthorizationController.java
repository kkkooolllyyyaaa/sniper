package com.tsypk.sniper.rest;

import com.tsypk.sniper.exception.BadRequestException;
import com.tsypk.sniper.model.entity.user.Role;
import com.tsypk.sniper.model.entity.user.Status;
import com.tsypk.sniper.model.entity.user.User;
import com.tsypk.sniper.network.UserDTO;
import com.tsypk.sniper.network.UserRequestValidator;
import com.tsypk.sniper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tsypk on 03.02.2022 03:26
 * @project sniper
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class AuthorizationController {
    private final UserService userService;
    private final UserRequestValidator validator;

    @Autowired
    public AuthorizationController(UserService userService, UserRequestValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        validator.validate(userDTO);
        if (userService.userExistByName(userDTO.getUsername()))
            throw new BadRequestException("user with username: " + userDTO.getUsername() + " is already exists");

        User user = convertFromDTO(userDTO);
        userService.register(user);
        log.info("[register] – new user registered with username: {} & password: {}", userDTO.getUsername(), userDTO.getPassword());
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    private User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }
}
