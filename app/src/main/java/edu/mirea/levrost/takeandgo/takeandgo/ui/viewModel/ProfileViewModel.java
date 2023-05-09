package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.ProfileListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public LiveData<List<Profile>> getProfilesByListId(List<String> idToFind) {
        return repo.getDataBaseProfilesByListId(idToFind);
    }

    public LiveData<List<Profile>> getProfilesById(String idToFind){
        return repo.getDataBaseProfilesById(idToFind);
    }

    public LiveData<Profile> getProfileById(String idToFind){
        return repo.getDataBaseProfileById(idToFind);
    }

    public void pushDataAll(List<Profile> profiles){
        for (int i = 0; i < profiles.size(); i++){
            repo.updateData(profiles.get(i));
        }
    }

    public void generic(){
        repo.generic();
    }

}
