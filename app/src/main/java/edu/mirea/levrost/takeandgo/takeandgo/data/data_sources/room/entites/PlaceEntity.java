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

    public PlaceEntity(){}

    public PlaceEntity(long id, String name, double latitude, double longitude){
        this.id = id;
        this.icon = "default_question_mark";
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = "";
    }

    public PlaceEntity(long id, String name, String icon, double latitude, double longitude){
        this(id, name, latitude, longitude);
        this.icon = icon;
    }

    public PlaceEntity(long id, String name, String icon, double latitude, double longitude, String description) {
        this(id, name, icon, latitude, longitude);
        this.description = description;
    }

//    public Place toDomainModel(){
//        return new Place(name, icon, latitude, longitude);
//    }

}
