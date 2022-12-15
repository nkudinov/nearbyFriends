package com.example.nearbyfriends.domain;

import java.io.Serializable;

public class UserLocation implements Serializable {
    public int userId;
    public double lat;
    public double lon;

    @Override
    public String toString() {
        return "UserLocation{" + "userId=" + userId + ", lat=" + lat + ", lon=" + lon + '}';
    }

    public UserLocation(int userId, double lat, double lon) {
        this.userId = userId;
        this.lat = lat;
        this.lon = lon;
    }
}
