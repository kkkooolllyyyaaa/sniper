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

import java.security.Principal;
import java.util.List;

/**
 * @author tsypk on 03.02.2022 03:26
 * @project sniper
 */
@RestController
@CrossOrigin
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

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(Principal principal) {
        if (principal.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UserDTO response = convertToDTO(userService.findByUsername(principal.getName()));
        log.info("[login] - user: {} is logged in", principal.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('users:read')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/logout")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> logout(Principal principal) {
        if (principal.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        log.info("[logout] - user: {} is logged out", principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> logoutPost(Principal principal) {
        if (principal.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        log.info("[logoutPost] - user: {} is logged out", principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
