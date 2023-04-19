package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivityMainBinding;

import java.util.List;


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
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//        fragmentManager = getSupportFragmentManager();
        navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_activity_container))
                .getNavController();

        navController.setGraph(R.navigation.login_graph);

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentListener, true);

        if (isLogin()){
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.login_graph);
            navGraph.setStartDestination(R.id.mainScreenFragment);
            navController.setGraph(navGraph);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentListener);
    }

    private FragmentManager.FragmentLifecycleCallbacks fragmentListener = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            navController = Navigation.findNavController(v);
        }
    };

    private Boolean isLogin(){
        Boolean isLogin = getIntent().getBooleanExtra("isLogin", false);
        return isLogin;
    }

    @Override
    public void onBackPressed() {
        if (navController == null || navController.getBackQueue().last().equals(navController.getBackQueue().first()) || // Костыль - проверка на то, сколько у меня в бэке, чтобы не выйти за его пределы для UI
                (isLogin() && navController.getBackQueue().size() <= 3) || (!isLogin() && navController.getBackQueue().size() <= 2)){ //Если не создали navController или пользователь не залогинен
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

        }
    }

}