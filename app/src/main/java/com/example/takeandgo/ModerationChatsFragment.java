package com.example.takeandgo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.takeandgo.databinding.ModerationChatsFragmentBinding;

public class ModerationChatsFragment extends Fragment {

    private ModerationChatsFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ModerationChatsFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

}
