package com.example.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.takeandgo.databinding.ProfileOnCommunityScreenFragmentBinding;

public class ProfileOnCommunityScreenFragment extends Fragment {

    private ProfileOnCommunityScreenFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ProfileOnCommunityScreenFragmentBinding.inflate(inflater, container, false);
        mBinding.profileIcon.setClipToOutline(true);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
