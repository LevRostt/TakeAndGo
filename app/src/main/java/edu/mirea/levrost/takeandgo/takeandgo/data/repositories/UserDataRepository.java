package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.UserInfoDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers.UserMapper;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.UserDataEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;

public class UserDataRepository {
    private UserInfoDataSource userData;
    private AppDataBase dataBaseSource;

    public UserDataRepository(Application application){
        this.userData = new UserInfoDataSource();
        this.dataBaseSource = AppDataBase.getDataBase(application);
    }

    public LiveData<UserData> getUserDataBase() {
        return Transformations.map(
            dataBaseSource.userDataDao().getData(), UserMapper::toDomainModel);}

    public void deleteUser(UserData data){
        AppDataBase.databaseWriteExecutor.execute(() ->{
            dataBaseSource.userDataDao().deleteUser(new UserDataEntity());
        });
    }

    public void updateData(UserData data){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            dataBaseSource.userDataDao().addProfile(new UserDataEntity(data.getName(), data.getUserId(), data.getIdOfVisitedPlaces() , data.getLatitude(), data.getLongitude()));
//            dataBaseSource.userDataDao().updateUser(new UserDataEntity(data.getName(),data.getUserId(), data.getIdOfVisitedPlaces() ,data.getLatitude(),data.getLongitude()));
        });
    }
}
