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

import com.example.takeandgo.databinding.MaplistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.adapters.PlaceListMapListRVAdapter;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;

public class MapListFragment extends Fragment {

    private MaplistFragmentBinding mBinding;
    private PlaceViewModel mPaceViewModel;
    private UserViewModel mUserViewModel;

    public static final String REQUEST_CODE_FOR_LATITUDE = "EXTRA_LATITUDE_DATA";
    public static final String KEY_FOR_DATA = "KEY_PLACE_CORD_DATA";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = MaplistFragmentBinding.inflate(inflater, container, false);
        mPaceViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mUserViewModel.updateData();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.rvPlacelist.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvPlacelist.setAdapter(new PlaceListMapListRVAdapter(this));

        mUserViewModel.getData().observe(getViewLifecycleOwner(), (data) ->{
            ((PlaceListMapListRVAdapter) mBinding.rvPlacelist.getAdapter()).updateData(data);

            mBinding.buttonToModerationChats.setOnClickListener(v -> {
                NavHostFragment.findNavController(this).navigate(MapListFragmentDirections.actionMapListFragmentToModerationChatsFragment());
            });

            mPaceViewModel.getPlaces().observe(getViewLifecycleOwner(), (value) -> {

                List<Place> insertList = new ArrayList<>();
                new Thread(()->{
                    for (Place place : value){
                        int placeDistance = Place.calculateDistance(data.getLatitude(), data.getLongitude(), place);
                        if (placeDistance < Place.showDistance){
                            insertList.add(place);
                        }
                    }
                }).start();
                ((PlaceListMapListRVAdapter) mBinding.rvPlacelist.getAdapter()).updateData(insertList);
            });

        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
        mPaceViewModel = null;
        mUserViewModel = null;
    }
}
