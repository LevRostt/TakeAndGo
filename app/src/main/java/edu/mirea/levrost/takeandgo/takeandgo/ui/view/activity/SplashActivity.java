package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivitySplashBinding;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.ProfileViewModel;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mBinding;
    private PlaceViewModel mPlaceViewModel;
    private ProfileViewModel mProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onBackPressed() {
    }

}
