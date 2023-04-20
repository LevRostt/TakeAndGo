package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.MainScreenFragment;


public class MainActivity extends FragmentActivity {

//    private FragmentManager fragmentManager;
    private ActivityMainBinding mBinding;
    private NavController navController;
    private long back_pressed;
//    private FragmentTransaction fragmentTransaction;

//    public void onNavigationFragmentSelector(String name){
//
//        fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.main_fragment, RegisterFragment.class, null);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDataBase.buildDatabase(getApplication());
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//        fragmentManager = getSupportFragmentManager();
//        AppDataBase.getDataBase(this)

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
//        Boolean isLogin = getIntent().getBooleanExtra("isLogin", false);
        if (this.getSharedPreferences("UID", Context.MODE_PRIVATE).getString("id", null) != null){
            return true;
        }
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