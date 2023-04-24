package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDataEntity {
    @PrimaryKey
    public long id;

    public String name;
    public String userId;
    public double latitude;
    public double longitude;

    public UserDataEntity(){}

    public UserDataEntity(String name, String userId){
        this.name = name;
        this.userId = userId;
        this.id = 0;
    }

    public UserDataEntity(String name, String userId, double latitude, double longitude) {
        this(name, userId);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
