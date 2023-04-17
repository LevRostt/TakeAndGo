package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MainScreenFragmentBinding;
import com.example.takeandgo.databinding.MapFragmentBinding;


public class MapFragment extends Fragment {

    private MapFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = MapFragmentBinding.inflate(inflater, container, false);
        Log.d("TakeAndGoDev", "Inside Map");

        return mBinding.getRoot();
    }

}