package com.example.nearbyfriends.web;


import com.example.nearbyfriends.domain.UserFriend;
import com.example.nearbyfriends.repository.UserFriendsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/user-friends")
public class UserFriendController {

    public UserFriendController(UserFriendsRepository userFriendsRepository) {
        this.userFriendsRepository = userFriendsRepository;
    }

    private UserFriendsRepository userFriendsRepository;

    @PostMapping("/{userId}/{friendId}")
    ResponseEntity<UserFriend> createFriend(@PathVariable("userId") int userId, @PathVariable("friendId") int friendId) {
        UserFriend userFriend = new UserFriend(userId, friendId);
        userFriendsRepository.save(userFriend);
        return ResponseEntity.ok(userFriend);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<UserFriend>> findbyUser(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userFriendsRepository.findById(userId));
    }
}
