package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class UserDataEntity {
    @PrimaryKey
    public long id;

    public String name;
    public String userId;
    public double latitude;
    public double longitude;

    @TypeConverters({PlacesConvertor.class})
    public List<Long> idOfVisitedPlaces;

    @TypeConverters({FriendsConvertor.class})
    public List<String> idFriends;

    public UserDataEntity(){}

    public UserDataEntity(String name, String userId){
        this.name = name;
        this.userId = userId;
        this.id = 0;
        this.idOfVisitedPlaces = new ArrayList<>();
        this.idFriends = new ArrayList<>();
    }

    public UserDataEntity(String name, String userId, List<Long> idOfVisitedPlaces, List<String> idFriends){
        this(name, userId);
        this.idFriends = idFriends;
        this.idOfVisitedPlaces = idOfVisitedPlaces;
    }

    public UserDataEntity(String name, String userId, List<Long> idOfVisitedPlaces, List<String> idFriends,double latitude, double longitude) {
        this(name, userId, idOfVisitedPlaces, idFriends);
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

