package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfileEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String icon;
    public String name;
    public int rating;
    public String uId;

    public ProfileEntity(){}

    public ProfileEntity(String name, int rating, String userId){
        this.icon = "default_question_mark";
        this.name = name;
        this.rating = rating;
        this.uId = userId;
    }

    public ProfileEntity(String name, String icon, int rating, String userId) {
        this(name, rating, userId);
        this.icon = icon;
    }
}
