package com.example.nearbyfriends.service;

import com.example.nearbyfriends.domain.UserLocation;
import com.example.nearbyfriends.repository.UserLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLocationService {
    private final UserLocationRepository userLocationRepository;

    public UserLocationService(UserLocationRepository userLocationRepository) {
        this.userLocationRepository = userLocationRepository;
    }

    public void save(UserLocation userLocation) {
        userLocationRepository.save(userLocation);
    }

    public UserLocation findById(int userId) {
        return userLocationRepository.findById(userId);
    }
}
