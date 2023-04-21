package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MapFragmentBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;


public class MapFragment extends Fragment {

    private MapFragmentBinding mBinding;
    private MapView mapView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double userLongitude;
    private double userLatitude;
    private Point userPoint;
    private float zoomOnUser = 14.0f;
    private static final int REQUEST_LOCATION_PERMISSION = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = MapFragmentBinding.inflate(inflater, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) mBinding.mapView;
        mapView.getMap().move(
                new CameraPosition(new Point(55.751574, 37.6), 8.0f, 0.0f, 0.0f));

        if (!checkUserLocationAccess()) {
            requestUserLocation();
        } else {
            if (updateUserLocation()) {
                mapView.getMap().move(
                        new CameraPosition(userPoint, zoomOnUser, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 3), null);
            }
        }

//        mapView.getMap().addCameraListener((map, cameraPosition, cameraUpdateReason, b) -> {
//            mBinding.userLocationBtm.setImageDrawable(getResources().getDrawable(R.drawable.map_arrow_empty));
//        });

        mBinding.userLocationBtm.setOnClickListener((v) ->{
            if (!checkUserLocationAccess()) {
                requestUserLocation();
            } else {
                if (updateUserLocation()) {
                    mapView.getMap().move(
                            new CameraPosition(userPoint, zoomOnUser, 0.0f, 0.0f),
                            new Animation(Animation.Type.SMOOTH, 2), null);
//                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.map_arrow));
                }
            }
        });



//        mapView.getMap().move(
//                new CameraPosition(new Point(55.751574, 37.625), 10.5f, 0.0f, 0.0f),
//                new Animation(Animation.Type.SMOOTH, 3),
//                null);


    }

    @Override
    public void onStart() {
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    private boolean checkUserLocationAccess() {
        return ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestUserLocation() {
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }

    private boolean updateUserLocation() {

        //Without this condition, the code receiver generates an error
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(getActivity(), task -> {
            if (task.getResult() != null) {
                Location location = task.getResult();
                userLongitude = location.getLongitude();
                userLatitude = location.getLatitude();
                userPoint = new Point(userLatitude, userLongitude);
            }
        });

        if (userPoint == null){
            return false;
        }
        else {return true;}
    }
}