package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;

public class UserInfoDataSource {

    public LiveData<UserData> createUserInfo(){
        MutableLiveData<UserData> result = new MutableLiveData<>();
        result.postValue(new UserData("","0"));
        return result;
    }

}
