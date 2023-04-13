package com.example.takeandgo.data.models;

public class PlaceList {
    private String icon;
    private String name;
    private double latitude;
    private double longitude;
    private String description;

    public PlaceList(String name, String icon, double latitude, double longitude) {
        this.icon = icon;
        this.name = name;
        this.latitude = latitude;
        this.latitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
