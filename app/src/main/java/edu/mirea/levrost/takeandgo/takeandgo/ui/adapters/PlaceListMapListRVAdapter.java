package edu.mirea.levrost.takeandgo.takeandgo.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.MapListFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.MaplistFragmentBinding;
import com.example.takeandgo.databinding.PlaceOnMaplistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PlaceListMapListRVAdapter extends RecyclerView.Adapter<PlaceListMapListRVAdapter.PlaceListViewHolder> {
    List<Place> data;
    private MapListFragment fragment;
    private MaplistFragmentBinding parentBinding;

    public PlaceListMapListRVAdapter(MapListFragment fragment, MaplistFragmentBinding binding){
        this.data = new ArrayList<>();
        this.fragment = fragment;
        parentBinding = binding;
    }

    public PlaceListMapListRVAdapter(List<Place> data){ this.data = data; }

    public void updateData(List<Place> newData) {
        data = newData;
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
        if (data.get(position) != null) {
            int resId = context.getResources().getIdentifier(data.get(position).getIcon(), "drawable", context.getPackageName());
            holder.binding.placeIcon.setImageResource(resId);
            holder.binding.placeName.setText(data.get(position).getName());
            holder.binding.distance.setText(String.valueOf(Math.round(data.get(position).getLatitude())));

            holder.binding.placeIcon.setClipToOutline(true);
            holder.binding.placeIcon.setCropToPadding(true);

            holder.binding.showButton.setOnClickListener( v -> {
//                NavHostFragment.findNavController(fragment).popBackStack(); // Починить
//                NavHostFragment.findNavController(fragment).getPreviousBackStackEntry().getSavedStateHandle().set("Latitude",10);
                Bundle bundle = new Bundle();
                bundle.putDoubleArray(fragment.KEY_FOR_DATA, new double[]{data.get(position).getLatitude(), data.get(position).getLongitude()});

                fragment.getParentFragmentManager().setFragmentResult(fragment.REQUEST_CODE_FOR_LATITUDE, bundle);
                NavHostFragment.findNavController(fragment).popBackStack();
            });

            holder.itemView.setAnimation(AnimationUtils.
                    loadAnimation(context, context.
                            getResources().
                            getIdentifier("fade_out", "anim", context.getPackageName()))); // Получение созданной заранее анимации в fade_out
        }
    }


    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }

    class PlaceListViewHolder extends RecyclerView.ViewHolder{

        public PlaceOnMaplistFragmentBinding binding;

        public PlaceListViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = PlaceOnMaplistFragmentBinding.bind(itemView);
        }

    }

}
