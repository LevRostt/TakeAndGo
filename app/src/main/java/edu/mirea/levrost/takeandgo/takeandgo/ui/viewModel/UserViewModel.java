package edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.data.repositories.UserDataRepository;

public class UserViewModel extends AndroidViewModel {
    private UserDataRepository repo;
    private LiveData<UserData> data;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.repo = new UserDataRepository(application);

        this.data = repo.getUserDataBase();
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<UserData> getData(){
        updateData();
        return data;
    }

    public void insertData(UserData userData){
        repo.updateData(userData);
    }

    public boolean updateData(){
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(getApplication().getMainExecutor(), task -> {
            if (task.getResult() != null && task.getResult().getLatitude() != 0 && data.getValue() != null) {
                Location location = task.getResult();

                UserData tempData = new UserData(data.getValue().getName(),
                        data.getValue().getUserId(),
                        data.getValue().getIdOfVisitedPlaces(),
                        location.getLatitude(),
                        location.getLongitude());

                repo.updateData(tempData);
                this.data = repo.getUserDataBase();
            }
        });
        return true;
    }

    public void deleteUserData(UserData userdata){
        repo.deleteUser(userdata);
    }

    public void insertPlace(long idToAdd){
        data.observe(getApplication(), user -> {
            //Добавить асинърон
            for (long id: user.getIdOfVisitedPlaces()){
                if (id == idToAdd){
                    return;
                }
            }
            user.addVisitPlace(idToAdd);

            repo.updateUserDataPlace(user);
        });
    };

}
