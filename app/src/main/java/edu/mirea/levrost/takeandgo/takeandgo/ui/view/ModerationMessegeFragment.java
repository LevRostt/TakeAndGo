package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.databinding.ModerationMessegeFragmentBinding;
import com.yandex.mapkit.geometry.Point;

import java.util.Arrays;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;

public class ModerationMessegeFragment extends Fragment {
    private ModerationMessegeFragmentBinding mBinding;
    public static final String key_to_data = "KEY_PLACE_POINTER";
    private Point selectedPlace;
    private PlaceViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = ModerationMessegeFragmentBinding.inflate(inflater, container, false);
//        mBinding.placeIcon.setCropToPadding(true);
        mViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        getParentFragmentManager().setFragmentResultListener(key_to_data, getViewLifecycleOwner(),
                (requestKey, result) -> {
                    double[] array = result.getDoubleArray(requestKey);
                    selectedPlace = new Point(array[0], array[1]);
                }
        );

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.pushToBtn.setOnClickListener(v->{

            if(mBinding.placeNameEditText.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Заполните, пожалуйста, поле имени", Toast.LENGTH_LONG).show();
            }
            else if(selectedPlace == null){
                Toast.makeText(getContext(), "Выберете, пожалуйста, место на карте", Toast.LENGTH_LONG).show();
            }
            else if(Arrays.toString(mBinding.placeRadius.getText().toString().split("")).isEmpty()){
                Toast.makeText(getContext(), "Введите, пожалуйста, радиус", Toast.LENGTH_LONG).show();
            }
            else{
                mViewModel.getPlaces().observe(getViewLifecycleOwner(), data ->{
                    mViewModel.addPlace(
                            new Place(
                                    data.size()+1,
                                    mBinding.placeNameEditText.getText().toString(),
                                    Long.parseLong(mBinding.placeRadius.getText().toString()),
                                    selectedPlace.getLatitude(),
                                    selectedPlace.getLongitude()));
                    NavHostFragment.findNavController(this).popBackStack();
                    NavHostFragment.findNavController(this).popBackStack();
                });
//                mViewModel.addPlace(
//                        new Place(
//                                mViewModel.getLastId(),
//                                mBinding.placeNameEditText.getText().toString(),
//                                Long.parseLong(mBinding.placeRadius.getText().toString()),
//                                selectedPlace.getLatitude(),
//                                selectedPlace.getLongitude()));
            }

        });

        mBinding.selectOnMap.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(ModerationMessegeFragmentDirections.actionModerationMessegeFragmentToMapSelectFragment());
        });

        mBinding.cancelBtn.setOnClickListener(v->{
            NavHostFragment.findNavController(this).popBackStack();
            NavHostFragment.findNavController(this).popBackStack();
        });
    }
}
