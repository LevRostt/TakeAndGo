package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.takeandgo.databinding.VisitlistFragmentBinding;
import edu.mirea.levrost.takeandgo.takeandgo.ui.adapters.PlaceListVisitListRVAdapter;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;


public class VisitListFragment extends Fragment {

    private VisitlistFragmentBinding mBinding;
    private PlaceViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = VisitlistFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        mBinding.rvPlacelist.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvPlacelist.setAdapter(new PlaceListVisitListRVAdapter());

        mBinding.toBackButton.setOnClickListener((v)->{
            NavHostFragment.findNavController(this).popBackStack();
        });

        mViewModel.getPlaces().observe(getViewLifecycleOwner(), (value) -> {
            ((PlaceListVisitListRVAdapter) mBinding.rvPlacelist.getAdapter()).updateData(value);
        });
    }
}
