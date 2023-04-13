package com.example.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.takeandgo.databinding.ModerationMessegeFragmentBinding;

public class ModerationMessegeFragment extends Fragment {
    private ModerationMessegeFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ModerationMessegeFragmentBinding.inflate(inflater, container, false);
        mBinding.placeIcon.setCropToPadding(true);

        return mBinding.getRoot();
    }
}
