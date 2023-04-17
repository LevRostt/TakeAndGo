package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.ProfileList;

import java.util.ArrayList;

public class ProfileListDataSource {

    public LiveData<ArrayList<ProfileList>> profiles(){
        MutableLiveData<ArrayList<ProfileList>> result = new MutableLiveData<>();

        new Thread(() -> {
            ArrayList<ProfileList> resultArr = new ArrayList<>();

            for (int i = 0; i < 25; i++){
                resultArr.add(new ProfileList("Test #" + (i+1), "samurai_image", i+1, i));
            }

            result.postValue(resultArr);
        }).start();

        return result;
    }

}
