package edu.mirea.levrost.takeandgo.takeandgo.data.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserData {
    private String name;
    private String userId;
    private double latitude = 0;
    private double longitude = 0;
    private List<Long> idOfVisitedPlaces;
    private List<String> idFriends;


    public UserData(String name, String userId){
        this.name = name;
        this.userId = userId;
        this.idFriends = new ArrayList<>();
        this.idOfVisitedPlaces = new ArrayList<>();
    }

    public UserData(String name, String userId, List<Long> idOfVisitedPlaces){
        this(name, userId);
        this.idOfVisitedPlaces = idOfVisitedPlaces;
    }

    public UserData(String name, String userId, List<Long> idOfVisitedPlaces, List<String> idFriends){
        this(name, userId, idOfVisitedPlaces);
        this.idFriends = idFriends;
    }

    public UserData(String name, String userId, List<Long> visitedPlaces, List<String> idFriends,  double latitude, double longitude){
        this(name, userId, visitedPlaces, idFriends);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public List<Long> getIdOfVisitedPlaces() {
        return idOfVisitedPlaces;
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

    public List<String> getIdFriends() { return idFriends; }

    public void addVisitPlace(long id){
        if (idOfVisitedPlaces.size() == 0){
            this.idOfVisitedPlaces = Collections.singletonList(id);
        }
        else{
            this.idOfVisitedPlaces.add(id);
        }
    }

    public void addFriend(String userId){
        if (idFriends.size() == 0){
            this.idFriends = Collections.singletonList(userId);
        }
        else{
            this.idFriends.add(userId);
        }
    }

    public void deleteFriend(String userId){
        if (idFriends != null){
            this.idFriends.remove(userId);
        }
    }
}
