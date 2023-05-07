package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MainCommunityFragmentBinding;


public class MainCommunityFragment extends Fragment {

    private MainCommunityFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = MainCommunityFragmentBinding.inflate(inflater, container, false);
        Log.d("TakeAndGoDev", "InsideMainScreenCreated");

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TakeAndGoDev", "InsideMainScreenView");
        NavController navController = ((NavHostFragment) getChildFragmentManager().findFragmentById(R.id.view_community_content_container)).getNavController();

//        navController.addOnDestinationChangedListener((navCtrl, destination, arguments) -> {
//            //addCont
//            if(destination.getId() == R.id.mapFragment) {
//                Toast.makeText(getContext(), "com",Toast.LENGTH_SHORT).show();
//            }
//        });

        NavigationUI.setupWithNavController(mBinding.topNavigationView, navController);
    }
}