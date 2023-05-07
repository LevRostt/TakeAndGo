package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.takeandgo.databinding.ProfileFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.ProfileViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding mBinding;
    private ProfileViewModel mProfileViewModel;
    private UserViewModel mUserViewModel;
    private Profile mProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ProfileFragmentBinding.inflate(inflater, container, false);
        mBinding.userIcon.setClipToOutline(true);
        mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        mBinding.friendsButton.setOnClickListener(view ->{
            Toast.makeText(getContext(), "friendsButton", Toast.LENGTH_LONG).show();
        });

        mBinding.jumpBack.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProfileViewModel.getProfileById(getArguments().getString("uid")).observe(getViewLifecycleOwner(),data->{
            mProfile = data;

            String uid = mProfile.getId();
            if (uid.length() <= 10){
                mBinding.userId.setText(uid);
            }
            else {
                mBinding.userId.setText(uid.substring(0, 10) + "...");
            }

//            if (getArguments().getBoolean("isFriend")){
//                mBinding.subscribe.setText("Вы подписаны");
//            }
//            else{
//                mBinding.subscribe.setText("Подписаться");
//            }

            mUserViewModel.getData().observe(getViewLifecycleOwner(), user -> {
                for (String friends : user.getIdFriends()) {
                    if (friends.equals(data.getId())) {
                        mBinding.subscribe.setText("Вы подписаны");
                        break;
                    } else {
                        mBinding.subscribe.setText("Подписаться");
                    }
                }

                mBinding.subscribe.setOnClickListener(v->{
                        if (user.getIdFriends().size() == 0){
                            mBinding.subscribe.setText("Вы подписаны");
                            mUserViewModel.insertFriend(mProfile.getId(), getViewLifecycleOwner());
                        }
                        else {
                            boolean isDrop = false;
                            for (String friends : user.getIdFriends()) {
                                if (friends.equals(data.getId())) {
                                    mBinding.subscribe.setText("Подписаться");
                                    mUserViewModel.deleteFriend(mProfile.getId(), getViewLifecycleOwner());
                                    isDrop = true;
                                    break;
                                } else {
                                    mBinding.subscribe.setText("Вы подписаны");
                                }
                            }
                            if (isDrop == false){
                                mUserViewModel.insertFriend(mProfile.getId(), getViewLifecycleOwner());
                            }
                        }
                });
            });

            mBinding.topNumber.setText("#"+data.getRating());
            mBinding.personName.setText(mProfile.getName());
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProfileViewModel = null;
        mUserViewModel = null;
    }
}