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

import java.util.ArrayList;
import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.adapters.PlaceListVisitListRVAdapter;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class VisitListFragment extends Fragment {

    private VisitlistFragmentBinding mBinding;
    private PlaceViewModel mPlaceViewModel;
    private UserViewModel mUserViewModel;


    public static final String REQUEST_CODE_FOR_LATITUDE = "EXTRA_LATITUDE_DATA";
    public static final String KEY_FOR_DATA = "KEY_PLACE_CORD_DATA";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = VisitlistFragmentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlaceViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        mBinding.rvPlacelist.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvPlacelist.setAdapter(new PlaceListVisitListRVAdapter(this));

        mBinding.toBackButton.setOnClickListener((v)->{
            NavHostFragment.findNavController(this).popBackStack();
        });

        mPlaceViewModel.getPlaces().observe(getViewLifecycleOwner(), (value) -> {

            mUserViewModel.getData().observe( getViewLifecycleOwner(), data ->{

                List<Place> toFill = new ArrayList<>();
                for (long idPlace : data.getIdOfVisitedPlaces()){ // to edit when add server database functionality
                    toFill.add(value.get((int) idPlace-1));
                }
                ((PlaceListVisitListRVAdapter) mBinding.rvPlacelist.getAdapter()).updateData(toFill);

            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlaceViewModel = null;
        mUserViewModel = null;
    }
}
