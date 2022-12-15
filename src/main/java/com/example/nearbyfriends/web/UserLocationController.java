package com.example.nearbyfriends.web;

import com.example.nearbyfriends.domain.UserLocation;
import com.example.nearbyfriends.service.UserLocationService;
import com.example.nearbyfriends.web.dto.UserLocationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-locations/")
public class UserLocationController {
    UserLocationService userLocationService;

    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PutMapping("/{userId}")
    public void createUserLocation(@PathVariable("userId") int userId, @RequestBody UserLocationRequest userLocationRequest) {
        UserLocation userLocation = new UserLocation(userId, userLocationRequest.lat(), userLocationRequest.lon());
        userLocationService.save(userLocation);
    }

    @GetMapping("/{userId}")
    public UserLocation findById(@PathVariable("userId") int userId) {
        return userLocationService.findById(userId);
    }

}
