package com.example.takeandgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.takeandgo.databinding.ProfilescreenFragmentBinding;


public class ProfileScreenFragment extends Fragment {

    private ProfilescreenFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ProfilescreenFragmentBinding.inflate(inflater, container, false);

        mBinding.friendsButton.setOnClickListener(view ->{

            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();

        });

        return mBinding.getRoot();
    }

}