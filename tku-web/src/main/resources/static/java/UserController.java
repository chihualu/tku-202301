package org.tku.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;

import java.util.Optional;

@RestController
@Log4j2
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/api/v1/user")
    public ResponseEntity<?> listUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/api/v1/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User createdUser = new User();
        createdUser.setUserId(user.getUserId());
        createdUser.setUserName(user.getUserName());
        createdUser.setPassword(passwordEncoder.encode("111111"));
        createdUser.setEmail(user.getEmail());
        createdUser.setRoleId(user.getRoleId());
        createdUser.setEnabled(user.getEnabled());
        userRepository.save(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/v1/user/{userId}")
    public ResponseEntity<?> modifyUser(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User createdUser = optionalUser.get();
        if(user.getUserName() != null) {
            createdUser.setUserName(user.getUserName());
        }
        if(user.getEmail() != null) {
            createdUser.setEmail(user.getEmail());
        }
        if (user.getRoleId() != null) {
            createdUser.setRoleId(user.getRoleId());
        }
        if (user.getEnabled() != null) {
            createdUser.setEnabled(user.getEnabled());
        }
        userRepository.save(createdUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
