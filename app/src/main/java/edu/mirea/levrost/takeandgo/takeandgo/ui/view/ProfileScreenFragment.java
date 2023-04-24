package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.takeandgo.databinding.ProfilescreenFragmentBinding;

import edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity.MainActivity;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class ProfileScreenFragment extends Fragment {

    private ProfilescreenFragmentBinding mBinding;
    private UserViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ProfilescreenFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        mBinding.userIcon.setClipToOutline(true);

        mBinding.friendsButton.setOnClickListener(view ->{

            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();

        });

        mBinding.placeListBtn.setOnClickListener(view -> {

            NavHostFragment.findNavController(this)
                    .navigate(ProfileScreenFragmentDirections.actionProfileFragmentToVisitListFragment());

        });

        mBinding.exitButton.setOnClickListener(view -> {
            getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE).edit().clear().apply();

            Log.d("TakeAndGoDev", getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE).getString("id", "base"));
            mViewModel.deleteUserData(mViewModel.getData().getValue());
            ( (MainActivity) getActivity()).navRestart();
        });


        mViewModel.getData().observe(getViewLifecycleOwner(), (data) -> {
            String name = String.valueOf(data.getUserId());
            if (name.length() <= 10){
                mBinding.userId.setText(name);
            }
            else {
                mBinding.userId.setText(String.valueOf(data.getUserId()).substring(0, 10) + "...");
            }
        });

        mViewModel.getData().observe(getViewLifecycleOwner(), (data) -> {
            mBinding.personName.setText(String.valueOf(data.getName()));
        });

        return mBinding.getRoot();
    }

}