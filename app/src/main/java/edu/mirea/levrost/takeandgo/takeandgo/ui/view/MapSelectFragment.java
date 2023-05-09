package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MapSelectedFragmentBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;


public class MapSelectFragment extends Fragment {

    private MapSelectedFragmentBinding mBinding;
    private MapView mapView;
    private MapKit mapKit;
    private MapObjectCollection mapObjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = MapSelectedFragmentBinding.inflate(inflater, container, false);

        mapView = (MapView) mBinding.mapView;
        mapKit = MapKitFactory.getInstance();
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.getMap().move(
                new CameraPosition(new Point(55.7515, 37.64), 9, 0.0f, 0.0f));

        mapView.getMap().addInputListener(inputListener);
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

    InputListener inputListener = new InputListener() {
        @Override
        public void onMapTap(@NonNull Map map, @NonNull Point point) {
            Toast.makeText(getContext(), point.getLatitude() + String.valueOf(point.getLongitude()) , Toast.LENGTH_SHORT).show();
            PlacemarkMapObject object = mapObjects.addPlacemark(new Point(point.getLatitude(), point.getLongitude()));

            object.setIcon(ImageProvider.fromResource(getContext(), R.drawable.pin),
                    new IconStyle().setAnchor(new PointF(0.5f, 0.7f))
                            .setScale(0.04f));
            object.setVisible(true);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Выбрать это место?.")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //pop back stack
                            Bundle bundle = new Bundle();
                            bundle.putDoubleArray(ModerationMessegeFragment.key_to_data, new double[]{point.getLatitude(), point.getLongitude()});
                            getParentFragmentManager().setFragmentResult(ModerationMessegeFragment.key_to_data, bundle);
                            NavHostFragment.findNavController(getParentFragment()).popBackStack();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем диалоговое окно
                            mapObjects.remove(object);
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        @Override
        public void onMapLongTap(@NonNull Map map, @NonNull Point point) {}
    };

}
