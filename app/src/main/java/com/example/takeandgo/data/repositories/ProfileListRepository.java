package com.example.takeandgo.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.takeandgo.data.data_sources.ProfileListDataSource;
import com.example.takeandgo.data.models.ProfileList;

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
