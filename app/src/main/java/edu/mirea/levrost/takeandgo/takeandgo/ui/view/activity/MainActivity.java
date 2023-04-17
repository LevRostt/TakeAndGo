package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivityMainBinding;


public class MainActivity extends FragmentActivity {

//    private FragmentManager fragmentManager;
    private ActivityMainBinding mBinding;
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
        Log.d("TakeAndGoDevInMainActivity", "Start MainActivity");
        NavController navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_activity_container))
                .getNavController();

        navController.setGraph(R.navigation.login_graph);

        if (isLogin()){
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.login_graph);
            navGraph.setStartDestination(R.id.mainScreenFragment);
            navController.setGraph(navGraph);
        }

    }



    private Boolean isLogin(){
        Boolean isLogin = getIntent().getBooleanExtra("isLogin", false);
        return isLogin;
    }

}