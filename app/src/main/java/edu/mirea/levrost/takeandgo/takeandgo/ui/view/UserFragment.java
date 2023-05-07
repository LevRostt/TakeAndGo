package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.UserFragmentBinding;

import edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity.MainActivity;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class UserFragment extends Fragment {

    private UserFragmentBinding mBinding;
    private UserViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = UserFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        mBinding.userIcon.setClipToOutline(true);

        mBinding.friendsButton.setOnClickListener(view ->{
            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();

            Bundle bundle = new Bundle();
            bundle.putBoolean("isFriends", true);
            NavHostFragment.findNavController(this).navigate(R.id.action_userFragment_to_community_screen, bundle);
        });

        mBinding.placeListBtn.setOnClickListener(view -> {
            NavHostFragment.findNavController(this)
                    .navigate(UserFragmentDirections.actionProfileFragmentToVisitListFragment());
        });

        mBinding.exitButton.setOnClickListener(view -> {
            getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE).edit().clear().apply();

            Log.d("TakeAndGoDev", getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE).getString("id", "base"));
            mViewModel.deleteUserData(mViewModel.getData().getValue());
            ( (MainActivity) getActivity()).navRestart();
        });


        mViewModel.getData().observe(getViewLifecycleOwner(), (data) -> {
            String uid = String.valueOf(data.getUserId());
            if (uid.length() <= 10){
                mBinding.userId.setText(uid);
            }
            else {
                mBinding.userId.setText(uid.substring(0, 10) + "...");
            }

            mBinding.personName.setText(String.valueOf(data.getName()));

        });

        return mBinding.getRoot();
    }

}