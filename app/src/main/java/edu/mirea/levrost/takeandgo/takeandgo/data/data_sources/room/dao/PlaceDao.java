package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM PlaceEntity")
    LiveData<List<PlaceEntity>> getAllPlaces();

    @Insert
    void addPlace(PlaceEntity place);

    @Delete
    void deletePlace(PlaceEntity place);


//    @Query("SELECT * FROM PlaceEntity WHERE id = :id")
//    LiveData<PlaceEntity> getById(long id);

}
