package edu.mirea.levrost.takeandgo.takeandgo.ui.adapters;

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
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.VisitListFragment;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.PlaceOnVisitlistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PlaceListVisitListRVAdapter extends RecyclerView.Adapter<PlaceListVisitListRVAdapter.PlaceListViewHolder> {
    List<Place> placesData;
    private VisitListFragment fragment;
    private UserViewModel mViewModel;


    public PlaceListVisitListRVAdapter(){ this.placesData = new ArrayList<>(); }
    public PlaceListVisitListRVAdapter(VisitListFragment fragment){
        this.placesData = new ArrayList<>();
        this.fragment = fragment;
    }

    public void updateData(List<Place> newData) {
        placesData = newData;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlaceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceListViewHolder(
                PlaceOnVisitlistFragmentBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent,false)
                        .getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListViewHolder holder, int position) {
        holder.binding.placeIcon.setClipToOutline(true); // Базовая настройка иконок для корректного отображения(появления закругления)

        Context context = holder.itemView.getContext();
        int resId = context.getResources().getIdentifier(placesData.get(position).getIcon(), "drawable", context.getPackageName());
        holder.binding.placeIcon.setImageResource(resId);
        holder.binding.placeName.setText(placesData.get(position).getName());

        holder.binding.placeIcon.setClipToOutline(true);
        holder.binding.placeIcon.setCropToPadding(true);

        holder.binding.showButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putDoubleArray(fragment.KEY_FOR_DATA, new double[]{placesData.get(position).getLatitude(), placesData.get(position).getLongitude()});

            fragment.getParentFragmentManager().setFragmentResult(fragment.REQUEST_CODE_FOR_LATITUDE, bundle);
            NavHostFragment.findNavController(fragment).popBackStack(R.id.mapFragment, false);
        });

        holder.itemView.setAnimation(AnimationUtils. // Получение созданной заранее анимации в fade_out
                    loadAnimation(context, context.
                            getResources().
                            getIdentifier("fade_out", "anim", context.getPackageName())));

    }


    @Override
    public int getItemCount() {
        if (placesData == null){
            return 0;
        }
        return placesData.size();
    }

    class PlaceListViewHolder extends RecyclerView.ViewHolder{

        public PlaceOnVisitlistFragmentBinding binding;

        public PlaceListViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = PlaceOnVisitlistFragmentBinding.bind(itemView);
        }

    }

}
