package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.ProfileListDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.ProfileList;

import java.util.ArrayList;

public class ProfileListRepository {
    private ProfileListDataSource mDataSource;

    public ProfileListRepository(){
        this.mDataSource = new ProfileListDataSource();
    }

    public LiveData<ArrayList<ProfileList>> getTestData(){
        return mDataSource.profiles();
    }

}
