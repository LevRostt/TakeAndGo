package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceListDataSource {

    public LiveData<List<Place>> places(){
        MutableLiveData<List<Place>> result = new MutableLiveData<>();

        new Thread(() -> {
            ArrayList<Place> resultArr = new ArrayList<>();

            for (int i = 0; i < 25; i++){
                resultArr.add(new Place("Test #" + (i+1), "samurai_image" , i+10, i +10));
            }

            result.postValue(resultArr);
        }).start();

        return result;
    }

}
