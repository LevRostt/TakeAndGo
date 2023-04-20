package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity")
    LiveData<List<ProfileEntity>> getAllProfiles();

    @Insert
    void addProfile(ProfileEntity profile);

    @Delete
    void deleteProfile(ProfileEntity profile);




//    @Query("SELECT * FROM PlaceEntity WHERE id = :id")
//    LiveData<PlaceEntity> getById(long id);

}
