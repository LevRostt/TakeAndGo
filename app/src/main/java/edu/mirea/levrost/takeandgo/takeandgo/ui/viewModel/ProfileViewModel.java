package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.ProfileListRepository;

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

    public void pushDataAll(List<Profile> profiles){
        for (int i = 0; i < profiles.size(); i++){
            repo.updateData(profiles.get(i));
        }
    }

    public void generic(){
        repo.generic();
    }

}
