package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.PlaceListDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers.PlaceMapper;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceListRepository {
    private PlaceListDataSource mDataSource;
    private AppDataBase dataBaseSource;

    public PlaceListRepository(Application application){
        this.mDataSource = new PlaceListDataSource();
        this.dataBaseSource = AppDataBase.getDataBase(application);
    }

    public LiveData<List<Place>> getTestData(){
        return mDataSource.places();
    }

    public LiveData<List<Place>> getDataBaseData(){
        return Transformations.map(
                dataBaseSource.placeDao().getAllPlaces(),
                (value) -> value.stream().map(PlaceMapper::toDomainModel).collect(Collectors.toList()));
    }


}
