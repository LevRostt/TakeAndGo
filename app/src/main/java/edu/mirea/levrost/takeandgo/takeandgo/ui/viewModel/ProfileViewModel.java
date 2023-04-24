package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.ProfileListRepository;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileListRepository repo;
    private LiveData<List<Profile>> mProfiles;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.repo = new ProfileListRepository(application);
        //mProfiles = repo.getTestData();
        mProfiles = repo.getDataBaseData();
    }

    public LiveData<List<Profile>> getProfiles() {return mProfiles;}



}
