package com.example.nearbyfriends.domain;

import java.time.LocalDate;


public class User {
    public int userId;
    public String userName;

    public LocalDate birthdate;

    public User(int userId, String userName, LocalDate birthdate) {
        this.userId = userId;
        this.userName = userName;
        this.birthdate = birthdate;
    }
}
