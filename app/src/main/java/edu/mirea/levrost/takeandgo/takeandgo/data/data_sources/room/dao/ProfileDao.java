package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity")
    LiveData<List<ProfileEntity>> getAllProfiles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProfile(ProfileEntity profile);

    @Query("SELECT * FROM ProfileEntity WHERE uId IN (:id)")
    LiveData<List<ProfileEntity>> getProfilesByListId(List<String> id);

    @Query("SELECT * FROM ProfileEntity WHERE uId LIKE :id")
    LiveData<List<ProfileEntity>> getProfilesById(String id);

    @Query("SELECT * FROM ProfileEntity WHERE uId = :id")
    LiveData<ProfileEntity> getProfileById(String id);


    @Delete
    void deleteProfile(ProfileEntity profile);




//    @Query("SELECT * FROM PlaceEntity WHERE id = :id")
//    LiveData<PlaceEntity> getById(long id);

}
