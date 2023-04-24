package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MapFragmentBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class MapFragment extends Fragment {

    private MapFragmentBinding mBinding;
    private MapView mapView;
//    private FusedLocationProviderClient fusedLocationProviderClient;
    private double userLatitude = 55.7515;
    private double userLongitude = 37.64;
//    private float userAzimuth;
    private Point userPoint;
    private float zoomOnUser = 9.5f;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private MapKit mapKit;
    private UserLocationLayer userLocationLayer;

    private UserViewModel mViewModel;

//    private MyAzimuthListener azimuthListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = MapFragmentBinding.inflate(inflater, container, false);
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mViewModel =  new ViewModelProvider(getActivity()).get(UserViewModel.class);
        mapView = (MapView) mBinding.mapView;
        mapKit = MapKitFactory.getInstance();
        updateUserLocation();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mapView.getMap().move(
//                new CameraPosition(new Point(userLongitude,userLatitude), zoomOnUser, 0.0f, 0.0f));

//        mViewModel.getData().observe(getViewLifecycleOwner(), (Observer<UserData>) userData -> {
//            userLatitude = userData.getLatitude();
//            userLongitude = userData.getLongitude();
//            userPoint = new Point(userLatitude, userLongitude);
//        });

        if (!checkUserLocationAccess()) {
            requestUserLocation();
            mapView.getMap().move(
                    new CameraPosition(new Point(userLatitude, userLongitude), zoomOnUser, 0.0f, 0.0f));
        } else {
            if (updateUserLocation()) {
                mapView.getMap().move(
                        new CameraPosition(userPoint, zoomOnUser, 0.0f, 45f),
                        new Animation(Animation.Type.SMOOTH, 3), null);
            }
        }

        mapKit.resetLocationManagerToDefault();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);

        userLocationLayer.setObjectListener(locationObjectListener);

//        mapView.getMap().addCameraListener((map, cameraPosition, cameraUpdateReason, b) -> {
//            mBinding.userLocationBtm.setImageDrawable(getResources().getDrawable(R.drawable.map_arrow_empty));
//        });

        mBinding.userLocationBtm.setOnClickListener((v) ->{
            if (!checkUserLocationAccess()) {
                requestUserLocation();
            } else {
                if (updateUserLocation()) {
                    updateUserLocation();

                    if (userPoint.getLatitude() !=0) {
                        mapView.getMap().move(
                                new CameraPosition(userPoint, zoomOnUser, 0.0f, 45f),
                                new Animation(Animation.Type.SMOOTH, 2), null);
                    }
//                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.map_arrow));
                }
                else {
                    Toast.makeText(getActivity(), "Please, turn on your location", Toast.LENGTH_SHORT).show();
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

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        //Without this condition, the code receiver generates an error
        mViewModel.getData().observe(getViewLifecycleOwner(), userData -> {
            userLatitude = userData.getLatitude();
            userLongitude = userData.getLongitude();
            zoomOnUser = 16.5f;
        });

        userPoint = new Point(userLatitude, userLongitude);

        if (userPoint == null){
            return false;
        }
        else {return true;}
    }


    private UserLocationObjectListener locationObjectListener = new UserLocationObjectListener() {
        @Override
        public void onObjectAdded(@NonNull UserLocationView userLocationView) {
            userLocationLayer.setAnchor(
                    new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
                    new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));

            userLocationView.getAccuracyCircle().setFillColor(Color.argb(200,233,249,228));

            userLocationView.getArrow().setIcon(ImageProvider.fromResource(getActivity(),R.drawable.pin),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                    .setRotationType(RotationType.NO_ROTATION)
                    .setScale(0.045f));

            userLocationView.getPin().setIcon(ImageProvider.fromResource(getActivity(),R.drawable.pin),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setRotationType(RotationType.NO_ROTATION)
                            .setScale(0.045f));

        }

        @Override
        public void onObjectRemoved(@NonNull UserLocationView userLocationView) {}

        @Override
        public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {}
    };


}