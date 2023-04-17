package edu.mirea.levrost.takeandgo.takeandgo.data.repositories;

import androidx.lifecycle.LiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.UserInfoDataSource;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.AppUserInfo;

public class UserInfoDataRepository {
    private UserInfoDataSource userInfo;

    public UserInfoDataRepository(){ this.userInfo = new UserInfoDataSource();}

    public LiveData<AppUserInfo> getUserInfo() { return userInfo.createUserInfo(); }

}
