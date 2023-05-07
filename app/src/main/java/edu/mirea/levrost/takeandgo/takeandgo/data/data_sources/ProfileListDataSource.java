package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

import java.util.ArrayList;

public class ProfileListDataSource {

    public LiveData<ArrayList<Profile>> profiles(){
        MutableLiveData<ArrayList<Profile>> result = new MutableLiveData<>();

        new Thread(() -> {
            ArrayList<Profile> resultArr = new ArrayList<>();

            for (int i = 0; i < 25; i++){
                resultArr.add(new Profile("Test #" + (i+1), "samurai_image", i+1, Integer.toString(i)));
            }

            result.postValue(resultArr);
        }).start();

        return result;
    }

}
