package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class UserData {
    private String name;
    private String userId;
    private double latitude = 0;
    private double longitude = 0;


    public UserData(String name, String userId){
        this.name = name;
        this.userId = userId;
    }

    public UserData(String name, String userId, double latitude, double longitude){
        this(name, userId);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUserId() {
        return userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
