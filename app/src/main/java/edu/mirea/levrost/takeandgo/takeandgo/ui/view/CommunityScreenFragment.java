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

import com.example.takeandgo.databinding.CommunityScreenFragmentBinding;
import edu.mirea.levrost.takeandgo.takeandgo.ui.adapters.ProfileListRVAdapter;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.ProfileViewModel;


public class CommunityScreenFragment extends Fragment {

    private CommunityScreenFragmentBinding mBinding;
    private ProfileViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = CommunityScreenFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        mBinding.rvProfilelist.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvProfilelist.setAdapter(new ProfileListRVAdapter());

        mViewModel.getProfiles().observe(getViewLifecycleOwner(), (value) -> {
            ((ProfileListRVAdapter) mBinding.rvProfilelist.getAdapter()).updateData(value);
        });
    }
}