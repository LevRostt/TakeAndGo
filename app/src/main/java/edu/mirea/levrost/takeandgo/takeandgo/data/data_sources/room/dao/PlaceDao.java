package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM PlaceEntity")
    LiveData<List<PlaceEntity>> getAllPlaces();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlace(PlaceEntity place);

    @Delete
    void deletePlace(PlaceEntity place);

    @Query("SELECT * FROM PlaceEntity WHERE latitude = :latitude AND longitude = :longitude")
    PlaceEntity findNameByLatitude(double latitude, double longitude);

    @Query("SELECT COUNT(*) FROM PlaceEntity")
    LiveData<Integer> count();

//    @Query("SELECT * FROM PlaceEntity WHERE id = :id")
//    LiveData<PlaceEntity> getById(long id);

}
