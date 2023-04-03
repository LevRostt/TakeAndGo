package com.example.takeandgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.databinding.MainscreenFragmentBinding;


public class MainScreenFragment extends Fragment {

    private MainscreenFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = MainscreenFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

}