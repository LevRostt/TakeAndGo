package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.PlaceListRepository;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewModel extends AndroidViewModel {
    private PlaceListRepository repo;
    private LiveData<List<Place>> mPlaces;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        this.repo = new PlaceListRepository(application);
//        mPlaces = repo.getTestData();
        mPlaces = repo.getDataBaseData();
    }

    public LiveData<List<Place>> getPlaces() {return mPlaces;}


}
