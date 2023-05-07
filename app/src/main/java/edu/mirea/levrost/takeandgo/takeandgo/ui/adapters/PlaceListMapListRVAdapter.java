package edu.mirea.levrost.takeandgo.takeandgo.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.MapListFragment;

import com.example.takeandgo.databinding.MaplistFragmentBinding;
import com.example.takeandgo.databinding.PlaceOnMaplistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PlaceListMapListRVAdapter extends RecyclerView.Adapter<PlaceListMapListRVAdapter.PlaceListViewHolder> {
    List<Place> placesData;
    UserData userData;
    private MapListFragment fragment;
//    private MaplistFragmentBinding parentBinding;

    public PlaceListMapListRVAdapter(MapListFragment fragment){
        this.placesData = new ArrayList<>();
        this.fragment = fragment;
//        parentBinding = binding;
    }

    //public PlaceListMapListRVAdapter(List<Place> data){ this.data = data; }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Place> newData) {
        placesData = newData;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(UserData newUserData) {
        userData = newUserData;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public PlaceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceListViewHolder(
                PlaceOnMaplistFragmentBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent,false)
                        .getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if (placesData.get(position) != null) {

            if (userData != null) {
                int placeDistance = Place.calculateDistance(userData.getLatitude(), userData.getLongitude(), placesData.get(position));

                holder.binding.placeLayout.setVisibility(View.VISIBLE);

                int resId = context.getResources().getIdentifier(placesData.get(position).getIcon(), "drawable", context.getPackageName());
                holder.binding.placeIcon.setImageResource(resId);
                holder.binding.placeName.setText(placesData.get(position).getName());
                holder.binding.distance.setText(String.valueOf(Math.round(placesData.get(position).getLatitude())));

                holder.binding.distance.setText(String.valueOf(placeDistance));

                holder.binding.placeIcon.setClipToOutline(true);
                holder.binding.placeIcon.setCropToPadding(true);

                holder.binding.showButton.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putDoubleArray(fragment.KEY_FOR_DATA, new double[]{placesData.get(position).getLatitude(), placesData.get(position).getLongitude()});

                    fragment.getParentFragmentManager().setFragmentResult(fragment.REQUEST_CODE_FOR_LATITUDE, bundle);
                    NavHostFragment.findNavController(fragment).popBackStack();
                });

                holder.itemView.setAnimation(AnimationUtils.
                        loadAnimation(context, context.
                                getResources().
                                getIdentifier("fade_out", "anim", context.getPackageName()))); // Получение созданной заранее анимации в fade_out
            } else {
                holder.binding.placeLayout.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public int getItemCount() {
        if (placesData == null){
            return 0;
        }
        return placesData.size();
    }

    class PlaceListViewHolder extends RecyclerView.ViewHolder{

        public PlaceOnMaplistFragmentBinding binding;

        public PlaceListViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = PlaceOnMaplistFragmentBinding.bind(itemView);
        }
    }



}
