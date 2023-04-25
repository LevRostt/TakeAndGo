package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.databinding.FragmentSplashBinding;

import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.root.AppDataBase;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity.MainActivity;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.ProfileViewModel;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding mBinding;
    private PlaceViewModel mPlaceViewModel;
    private ProfileViewModel mProfileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSplashBinding.inflate(inflater, container, false);

        mPlaceViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillDataBase(); // Temp realization filling data base on start

        new Thread(() -> {
            Intent intent = new Intent(getContext(), MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }).start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
        mPlaceViewModel = null;
        mProfileViewModel = null;
    }


    private void fillDataBase(){
        mPlaceViewModel.getPlaces().observe(getViewLifecycleOwner(), places -> {
            if (places.size() == 0){
                mPlaceViewModel.generic();
                mProfileViewModel.generic();
            }
        });

    }


//    private void renderAnimations() {
//        mBinding.loadingIndicator.setAlpha(0f);
//        mBinding.loadingIndicator.animate()
//                .alpha(0.7f)
//                .setDuration(500)
//                .start();
//
//        mBinding.pleaseWaitTextView.setAlpha(0f);
//        mBinding.pleaseWaitTextView.animate()
//                .alpha(1f)
//                .setStartDelay(200)
//                .setDuration(500)
//                .start();
//
//    }
}