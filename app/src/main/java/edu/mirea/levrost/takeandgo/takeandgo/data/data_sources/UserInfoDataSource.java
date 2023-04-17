package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Objects;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.AppUserInfo;

public class UserInfoDataSource {

    public LiveData<AppUserInfo> createUserInfo(){
        MutableLiveData<AppUserInfo> result = new MutableLiveData<>();
        result.postValue(new AppUserInfo("",0)); // test null user(unlogin)
        return result;
    }

}
