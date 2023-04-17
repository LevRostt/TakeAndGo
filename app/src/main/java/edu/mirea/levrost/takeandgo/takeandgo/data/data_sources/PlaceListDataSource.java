package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.PlaceList;

import java.util.ArrayList;

public class PlaceListDataSource {

    public LiveData<ArrayList<PlaceList>> places(){
        MutableLiveData<ArrayList<PlaceList>> result = new MutableLiveData<>();

        new Thread(() -> {
            ArrayList<PlaceList> resultArr = new ArrayList<>();

            for (int i = 0; i < 25; i++){
                resultArr.add(new PlaceList("Test #" + (i+1), "samurai_image" , i+10, i +10));
            }

            result.postValue(resultArr);
        }).start();

        return result;
    }

}
