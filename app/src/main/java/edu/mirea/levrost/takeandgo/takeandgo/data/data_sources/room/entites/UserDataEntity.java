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

    public UserDataEntity(){}

    public UserDataEntity(String name, String userId){
        this.name = name;
        this.userId = userId;
        this.id = 0;
        this.idOfVisitedPlaces = new ArrayList<Long>();
    }

    public UserDataEntity(String name, String userId, List<Long> idOfVisitedPlaces){
        this(name, userId);
        this.idOfVisitedPlaces = idOfVisitedPlaces;
    }

    public UserDataEntity(String name, String userId, List<Long> idOfVisitedPlaces ,double latitude, double longitude) {
        this(name, userId, idOfVisitedPlaces);
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

