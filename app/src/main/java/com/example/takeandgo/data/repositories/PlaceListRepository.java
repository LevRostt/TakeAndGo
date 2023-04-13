package com.example.takeandgo.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.takeandgo.data.data_sources.PlaceListDataSource;
import com.example.takeandgo.data.models.PlaceList;

import java.util.ArrayList;

public class PlaceListRepository {
    private PlaceListDataSource mDataSource;

    public PlaceListRepository(){
        this.mDataSource = new PlaceListDataSource();
    }

    public LiveData<ArrayList<PlaceList>> getTestData(){
        return mDataSource.places();
    }


}
