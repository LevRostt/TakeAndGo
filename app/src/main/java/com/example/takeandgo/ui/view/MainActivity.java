package com.example.takeandgo.ui.view;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ActivityMainBinding;


public class MainActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private ActivityMainBinding mBinding;
    private FragmentTransaction fragmentTransaction;

    public void onNavigationFragmentSelector(String name){

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment, RegisterFragment.class, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        fragmentManager = getSupportFragmentManager();


    }
}