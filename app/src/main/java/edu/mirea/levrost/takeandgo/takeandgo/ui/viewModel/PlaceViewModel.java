package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.PlaceList;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.PlaceListRepository;

import java.util.ArrayList;

public class PlaceViewModel extends AndroidViewModel {
    private PlaceListRepository repo;
    private LiveData<ArrayList<PlaceList>> mPlaces;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        this.repo = new PlaceListRepository();
        mPlaces = repo.getTestData();
    }

    public LiveData<ArrayList<PlaceList>> getPlaces() {return mPlaces;}


}
