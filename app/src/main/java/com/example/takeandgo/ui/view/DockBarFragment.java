package com.example.takeandgo.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.databinding.DockbarFragmentBinding;


public class DockBarFragment extends Fragment {

    private DockbarFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DockbarFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

}