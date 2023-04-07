package com.example.takeandgo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.takeandgo.databinding.PlaceOnMaplistFragmentBinding;

public class PlaceOnMapListFragment extends Fragment {

    private PlaceOnMaplistFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = PlaceOnMaplistFragmentBinding.inflate(inflater, container, false);
        mBinding.placeIcon.setClipToOutline(true);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
