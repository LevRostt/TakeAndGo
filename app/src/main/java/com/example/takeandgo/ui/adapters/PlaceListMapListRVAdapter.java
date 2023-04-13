package com.example.takeandgo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takeandgo.data.models.PlaceList;
import com.example.takeandgo.databinding.PlaceOnMaplistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PlaceListMapListRVAdapter extends RecyclerView.Adapter<PlaceListMapListRVAdapter.PlaceListViewHolder> {
    List<PlaceList> data;

    public PlaceListMapListRVAdapter(){ this.data = new ArrayList<>(); }

    public PlaceListMapListRVAdapter(List<PlaceList> data){ this.data = data; }

    public void updateData(List<PlaceList> newData) {
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
        int resId = context.getResources().getIdentifier(data.get(position).getIcon(), "drawable", context.getPackageName());
        holder.binding.placeIcon.setImageResource(resId);
        holder.binding.placeName.setText(data.get(position).getName());
        holder.binding.distance.setText(String.valueOf(Math.round(data.get(position).getLatitude())));
        holder.binding.placeIcon.setClipToOutline(true);

        holder.itemView.setAnimation(AnimationUtils.
                loadAnimation(context, context.
                getResources().
                getIdentifier("fade_out", "anim", context.getPackageName()))); // Получение созданной заранее анимации в fade_out
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
