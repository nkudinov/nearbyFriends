package com.example.nearbyfriends.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record UserRequest(@JsonProperty("user_name") String userName, LocalDate birthdate) {}
