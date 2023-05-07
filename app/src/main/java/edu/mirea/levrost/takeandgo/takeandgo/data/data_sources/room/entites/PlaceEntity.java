package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;

@Entity
public class PlaceEntity {

    @PrimaryKey
    public long id;

    @ColumnInfo(name = "icon_pass")
    public String icon;

    public String name;
    public double latitude;
    public double longitude;
    public String description;
    public long radius;

    public PlaceEntity(){}

    public PlaceEntity(long id, String name, long radius, double latitude, double longitude){
        this.id = id;
        this.icon = "default_question_mark";
        this.name = name;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = "";
    }

    public PlaceEntity(long id, String name, long radius, String icon, double latitude, double longitude){
        this(id, name, radius,latitude, longitude);
        this.icon = icon;
    }

    public PlaceEntity(long id, String name, long radius, String icon, double latitude, double longitude, String description) {
        this(id, name, radius, icon, latitude, longitude);
        this.description = description;
    }

//    public Place toDomainModel(){
//        return new Place(name, icon, latitude, longitude);
//    }

}
