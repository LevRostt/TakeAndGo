package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.UserInfoDataRepository;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.AppUserInfo;

public class UserViewModel extends AndroidViewModel {
    private UserInfoDataRepository repo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.repo = new UserInfoDataRepository();
    }

    public LiveData<AppUserInfo> isUserLogin(){
        return this.repo.getUserInfo();
    }
}
