package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.takeandgo.databinding.ProfileFragmentBinding;


public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ProfileFragmentBinding.inflate(inflater, container, false);
        mBinding.userIcon.setClipToOutline(true);

        mBinding.friendsButton.setOnClickListener(view ->{

            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();

        });

        return mBinding.getRoot();
    }

}