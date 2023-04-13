package com.example.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.takeandgo.databinding.DockbarCommunityFragmentBinding;


public class DockBarCommunityFragment extends Fragment {

    private DockbarCommunityFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DockbarCommunityFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

}