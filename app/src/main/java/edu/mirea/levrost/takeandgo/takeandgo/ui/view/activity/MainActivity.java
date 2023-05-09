package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivityMainBinding;
import com.yandex.mapkit.MapKitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.MainScreenFragment;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class MainActivity extends FragmentActivity {
    private ActivityMainBinding mBinding;
    private NavController navController;
    private long back_pressed;
//    private UserViewModel mViewModel;

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final String MAPKIT_API_KEY = "e919f593-7414-4fb9-88ec-76426ec26475";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        AppDataBase.buildDatabase(getApplication());

//        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);

//        AppDataBase.getDataBase(this).userDataDao().addProfile( this.getSharedPreferences("UID", Context.MODE_PRIVATE).getString("id", null) );

        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentListener, true);
        navRestart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentListener);
    }


    public void navRestart(){
        navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_activity_container))
                .getNavController();

        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.login_graph);

        if (isLogin()){
            ActivityCompat
                    .requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            ActivityCompat
                    .requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_PERMISSION);
            navGraph.setStartDestination(R.id.mainScreenFragment);
            navController.setGraph(navGraph);
        } else{
            navController.setGraph(navGraph);
        }
    }

    private FragmentManager.FragmentLifecycleCallbacks fragmentListener = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            if (navController != Navigation.findNavController(v)) {
                navController = Navigation.findNavController(v);
            }
        }
    };

    private Boolean isLogin(){
        String uId = getSharedPreferences("UID", Context.MODE_PRIVATE).getString("id", "0");

        if (!uId.equals("0")){
//            mViewModel.insertData(new UserData("Test name", uId, Arrays.asList(1L, 3L))); //Позже парсить имя и список из базы
//            mViewModel = null;
            return true;
        }

//        mViewModel = null;
        return false;
    }


    private boolean isStartDestnation(NavDestination destination){
        if (destination == null) return false;
        NavGraph graph = destination.getParent();
        if (graph == null) return false;
        List<Integer> startDestinations = Arrays.asList(R.id.loginFragment, R.id.mapFragment);
        return startDestinations.contains(destination.getId());
    }

    @Override
    public void onBackPressed() {
        if (isStartDestnation(navController.getCurrentDestination()) || navController.getBackQueue().isEmpty()){ //Если не создали navController или пользователь не залогинен
            if (back_pressed + 2500 > System.currentTimeMillis()) {
                super.onBackPressed();
            }
            else {
                Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
        }
        else {

            navController.popBackStack();
            Log.d("TakeAndGoDev_onBackPressed_pop", navController.getBackQueue().toString());

        }
    }


}