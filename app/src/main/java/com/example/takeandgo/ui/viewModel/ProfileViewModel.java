package com.example.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.takeandgo.data.models.ProfileList;
import com.example.takeandgo.data.repositories.ProfileListRepository;

import java.util.ArrayList;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileListRepository repo;
    private LiveData<ArrayList<ProfileList>> mPlaces;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.repo = new ProfileListRepository();
        mPlaces = repo.getTestData();
    }

    public LiveData<ArrayList<ProfileList>> getPlaces() {return mPlaces;}


}
