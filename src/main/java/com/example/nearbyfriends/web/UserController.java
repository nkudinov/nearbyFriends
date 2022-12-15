package com.example.nearbyfriends.web;

import com.example.nearbyfriends.domain.User;
import com.example.nearbyfriends.repository.UserRepository;
import com.example.nearbyfriends.web.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @PostMapping("/{userId}")

    public ResponseEntity<User> createUser(@PathVariable("userId") int userId, @RequestBody UserRequest userRequest) {
        log.info("create user with userId={}", userId);
        User user = new User(userId, userRequest.userName(), userRequest.birthdate());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> findUser(@PathVariable("userId") int userId) {
        log.info("find user by id = {}", userId);
        return ResponseEntity.ok(userRepository.findById(userId));
    }
}
