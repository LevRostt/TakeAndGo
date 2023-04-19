package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        mBinding.userIcon.setClipToOutline(true);

        mBinding.friendsButton.setOnClickListener(view ->{

            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();

        });

        mBinding.placeListBtn.setOnClickListener(view -> {

            NavHostFragment.findNavController(this).navigate(ProfileScreenFragmentDirections.actionProfileFragmentToVisitListFragment());

        });

        return mBinding.getRoot();
    }

}