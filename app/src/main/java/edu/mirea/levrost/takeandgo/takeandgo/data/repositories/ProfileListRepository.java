package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import android.app.Application;
import android.view.animation.Transformation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.ProfileListDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers.ProfileMapper;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

import java.util.ArrayList;
import java.util.Collections;
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

    public LiveData<List<Profile>> getDataBaseProfilesByListId(List<String> id){
        return Transformations.map(
                dataBaseSource.profileDao().getProfilesByListId(id),
                (value) -> value.stream().map(ProfileMapper::toDomainModel).collect(Collectors.toList()));
    }

    public LiveData<List<Profile>> getDataBaseProfilesById(String id){
        return Transformations.map(
                dataBaseSource.profileDao().getProfilesById("%"+id+"%"),
                (value) -> value.stream().map(ProfileMapper::toDomainModel).collect(Collectors.toList()));
    }

    public LiveData<Profile> getDataBaseProfileById(String id){
        return Transformations.map(
                dataBaseSource.profileDao().getProfileById(id),
                ProfileMapper::toDomainModel);
    }

    public void updateData(Profile data){
        AppDataBase.databaseWriteExecutor.execute(()->{
            dataBaseSource.profileDao().addProfile(new ProfileEntity(data.getName(), data.getIcon(), data.getRating(),data.getId()));
        });
    }

    public void generic(){
        AppDataBase.databaseWriteExecutor.execute(()->{
            List<Profile> profileList = new ArrayList<>();
            profileList.add(new Profile("User1",1,  "10"));
            profileList.add(new Profile("User2",2, "12"));
            profileList.add(new Profile("User3",3, "14"));
            profileList.add(new Profile("User4",4, "6"));
            profileList.add(new Profile("User5",5, "16"));
            profileList.add(new Profile("User6",666, "160"));

            for (Profile profile: profileList){
                dataBaseSource.profileDao().addProfile(new ProfileEntity( profile.getName(), profile.getRating(), profile.getId()));
            }
        });
    }
}
