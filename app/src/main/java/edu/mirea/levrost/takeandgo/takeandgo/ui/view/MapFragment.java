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
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.PlaceViewModel;
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
    private MapObjectCollection mapObjects;

    private UserViewModel mUserViewModel;
    private PlaceViewModel mPlaceViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = MapFragmentBinding.inflate(inflater, container, false);
        mUserViewModel =  new ViewModelProvider(getActivity()).get(UserViewModel.class);
        mPlaceViewModel =  new ViewModelProvider(getActivity()).get(PlaceViewModel.class);

        mapView = (MapView) mBinding.mapView;
        mapKit = MapKitFactory.getInstance();
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(false);
        userLocationLayer.setObjectListener(locationObjectListener);

        updateUserLocation();
        createMapPlaces();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!checkAvaibleUserLocationAccess()) {
            requestUserLocation();
            mapView.getMap().move(
                    new CameraPosition(new Point(userLatitude, userLongitude), zoomOnUser, 0.0f, 0.0f));
        } else {
            userLocationLayer.setVisible(true);
            jumpToUser(1.5f);
        }

        mBinding.userLocationBtm.setOnClickListener((v) ->{
            jumpToUser(2);
        });

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserViewModel = null;
        mPlaceViewModel = null;
    }

    private boolean checkAvaibleUserLocationAccess() {
        return ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestUserLocation() {
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        ActivityCompat
                .requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_PERMISSION);
    }

    private boolean updateUserLocation() { // Возвращает true - если обновлена, false - если нет возможности и установила по умолчанию

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        //Without this condition, the code receiver generates an error
        mUserViewModel.getData().observe(getViewLifecycleOwner(), userData -> {
            userLatitude = userData.getLatitude();
            userLongitude = userData.getLongitude();
            zoomOnUser = 16.5f;
        });

        userPoint = new Point(userLatitude, userLongitude);

        if (userLongitude == 0 || userLatitude == 0){
            userPoint = new Point(55.7515, 37.64);
            zoomOnUser = 9.5f;
            return false;
        }
        else {return true;}
    }

    private void jumpToUser(float cameraDuration){
        if (!checkAvaibleUserLocationAccess()) {
            requestUserLocation();
        } else {
            if (!updateUserLocation()) {
                Toast.makeText(getActivity(), "Please, turn on your location", Toast.LENGTH_SHORT).show();
            }
            userLocationLayer.setVisible(true);
            mapView.getMap().move(
                    new CameraPosition(userPoint, zoomOnUser, 0.0f, 45f),
                    new Animation(Animation.Type.SMOOTH, cameraDuration), null);
        }
    }

    private UserLocationObjectListener locationObjectListener = new UserLocationObjectListener() {
        @Override
        public void onObjectAdded(@NonNull UserLocationView userLocationView) {
//            userLocationLayer.setAnchor(
//                    new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
//                    new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));

            userLocationView.getAccuracyCircle().setFillColor(Color.argb(200,233,249,228));

            userLocationView.getArrow().setIcon(ImageProvider.fromResource(getActivity(),R.drawable.map_arrow_circle_red),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setRotationType(RotationType.NO_ROTATION)
                            .setScale(0.065f));

            userLocationView.getPin().setIcon(ImageProvider.fromResource(getActivity(),R.drawable.map_arrow_circle_red),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setRotationType(RotationType.NO_ROTATION)
                            .setScale(0.065f));

        }

        @Override
        public void onObjectRemoved(@NonNull UserLocationView userLocationView) {}

        @Override
        public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {}
    };

    private void createMapPlaces(){

        MapObjectTapListener objectTapListener = (mapObject, point) -> {

            PlacemarkMapObject localObject = (PlacemarkMapObject) mapObject;
            Object userData = localObject.getUserData();
            if (userData instanceof Place){
                Toast.makeText(getContext(),((Place) localObject.getUserData()).getName(), Toast.LENGTH_SHORT).show();
            }
            return true;
        };

        mPlaceViewModel.getPlaces().observe(getViewLifecycleOwner(), places -> {
            for (Place place: places){

                PlacemarkMapObject object = mapObjects.addPlacemark(new Point(place.getLatitude(),place.getLongitude()));
                object.setIcon(ImageProvider.fromResource(getContext(), R.drawable.pin),
                        new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                        .setScale(0.04f));
                object.setVisible(true);
                object.setUserData(place);

                object.addTapListener(objectTapListener);
            }
        });
    }

}