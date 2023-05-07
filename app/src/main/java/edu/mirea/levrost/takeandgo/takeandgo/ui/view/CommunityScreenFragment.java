package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.CommunityScreenFragmentBinding;
import edu.mirea.levrost.takeandgo.takeandgo.ui.adapters.ProfileListRVAdapter;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.ProfileViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class CommunityScreenFragment extends Fragment {

    private CommunityScreenFragmentBinding mBinding;
    private ProfileViewModel mProfileViewModel;
    private UserViewModel mUserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = CommunityScreenFragmentBinding.inflate(inflater, container, false);
        mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        return mBinding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.rvProfilelist.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvProfilelist.setAdapter(new ProfileListRVAdapter(this));

        updateInstance();

        mBinding.topCommunity.setOnClickListener(v -> {
            getArguments().putBoolean("isFriends", false);
            updateInstance();
        });

        mBinding.topFriends.setOnClickListener(v ->{
            getArguments().putBoolean("isFriends", true);
            updateInstance();
        });
    }

    private void updateInstance(){
        if (getArguments().getBoolean("isFriends") == false){
            mBinding.search.setVisibility(View.VISIBLE);
            mBinding.topCommunity.setEnabled(false);
            mBinding.topFriends.setEnabled(true);
            mBinding.topCommunity.setBackgroundResource(R.drawable.inner_dockbar_active);
            mBinding.topFriends.setBackgroundResource(R.drawable.inner_dockbar_bordered);

            mProfileViewModel.getProfiles().observe(getViewLifecycleOwner(), (value) -> {
                ((ProfileListRVAdapter) mBinding.rvProfilelist.getAdapter()).updateData(value);
            });
        }
        else{
            mBinding.search.setVisibility(View.GONE);
            mBinding.topFriends.setEnabled(false);
            mBinding.topCommunity.setEnabled(true);
            mBinding.topFriends.setBackgroundResource(R.drawable.inner_dockbar_active);
            mBinding.topCommunity.setBackgroundResource(R.drawable.inner_dockbar_bordered);

            mUserViewModel.getData().observe(getViewLifecycleOwner(), data -> {
                mProfileViewModel.getProfilesById(data.getIdFriends()).observe(getViewLifecycleOwner(), (value) -> {
                    ((ProfileListRVAdapter) mBinding.rvProfilelist.getAdapter()).updateData(value);
                });
            });
        }
    }
}