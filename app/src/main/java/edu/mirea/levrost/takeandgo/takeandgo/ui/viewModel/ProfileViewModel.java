package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.ProfileList;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.ProfileListRepository;

import java.util.ArrayList;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileListRepository repo;
    private LiveData<ArrayList<ProfileList>> mProfiles;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.repo = new ProfileListRepository();
        mProfiles = repo.getTestData();
    }

    public LiveData<ArrayList<ProfileList>> getProfiles() {return mProfiles;}


}
