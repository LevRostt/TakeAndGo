package edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
    }
}
