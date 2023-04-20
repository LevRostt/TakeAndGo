package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.ProfileListDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers.ProfileMapper;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileListRepository {
    private ProfileListDataSource mDataSource;
    private AppDataBase dataBaseSource;

    public ProfileListRepository(Application application){
        this.mDataSource = new ProfileListDataSource();
        this.dataBaseSource = AppDataBase.getDataBase(application);
    }

    public LiveData<ArrayList<Profile>> getTestData(){
        return mDataSource.profiles();
    }

    public LiveData<List<Profile>> getDataBaseData(){
        return Transformations.map(
                dataBaseSource.profileDao().getAllProfiles(),
                (value) -> value.stream().map(ProfileMapper::toDomainModel).collect(Collectors.toList()));
    }

}
