package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MainScreenFragmentBinding;


public class MainScreenFragment extends Fragment {

    private MainScreenFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = MainScreenFragmentBinding.inflate(inflater, container, false);
        Log.d("TakeAndGoDev", "InsideMainScreenCreated");

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TakeAndGoDev", "InsideMainScreenView");
        NavController navController = ((NavHostFragment) getChildFragmentManager().findFragmentById(R.id.view_content_container)).getNavController();
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView, navController);

    }
}