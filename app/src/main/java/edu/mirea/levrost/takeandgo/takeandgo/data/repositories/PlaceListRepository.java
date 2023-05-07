package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.PlaceListDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers.PlaceMapper;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

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

    public void updateData(Place data){
        AppDataBase.databaseWriteExecutor.execute(()->{
            dataBaseSource.placeDao().addPlace(new PlaceEntity(data.getId(), data.getName(), data.getRadius(), data.getIcon(), data.getLatitude(), data.getLongitude(), data.getDescription()));
        });
    }

    public void generic(){
        AppDataBase.databaseWriteExecutor.execute(()->{
            List<Place> placeList = new ArrayList<>();

            placeList.add(new Place(1, "Воробьёвы горы", 500,"sparrow_hills"  ,55.71, 37.545));
            placeList.add(new Place(2, "Парк Олимпийской Деревни", 500,55.6788, 37.4778));
            placeList.add(new Place(3, "Парк Никулино", 500,55.658378, 37.479546 ));
            placeList.add(new Place(4, "Парк Школьников", 500,55.668, 37.462 ));
            for (Place place: placeList){
                dataBaseSource.placeDao().addPlace(new PlaceEntity(place.getId(), place.getName(),place.getRadius(),place.getIcon(),place.getLatitude(),place.getLongitude()));
            }

        });
    }

    public Place findByCoordinates(double latitude, double longitude){
        return PlaceMapper.toDomainModel(dataBaseSource.placeDao().findNameByLatitude(latitude, longitude));
    }

}
