package edu.mirea.levrost.takeandgo.takeandgo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;
import com.example.takeandgo.databinding.PlaceOnVisitlistFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PlaceListVisitListRVAdapter extends RecyclerView.Adapter<PlaceListVisitListRVAdapter.PlaceListViewHolder> {
    List<Place> data;
    private int lastPosition = -1;

    public PlaceListVisitListRVAdapter(){ this.data = new ArrayList<>(); }
    public PlaceListVisitListRVAdapter(List<Place> data){ this.data = data; }

    public void updateData(List<Place> newData) {
        data = newData;

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
        int resId = context.getResources().getIdentifier(data.get(position).getIcon(), "drawable", context.getPackageName());
        holder.binding.placeIcon.setImageResource(resId);
        holder.binding.placeName.setText(data.get(position).getName());

        holder.itemView.setAnimation(AnimationUtils. // Получение созданной заранее анимации в fade_out
                    loadAnimation(context, context.
                            getResources().
                            getIdentifier("fade_out", "anim", context.getPackageName())));

//        if (holder.getAdapterPosition() < lastPosition) {
//
//            holder.itemView.setAlpha(0.0f);
//            Animation animation = AnimationUtils. // Получение созданной заранее анимации в fade_out
//                    loadAnimation(context, context.
//                            getResources().
//                            getIdentifier("fade_out", "anim", context.getPackageName()));
//            holder.itemView.startAnimation(animation);
//        } else {
//            // Clear animation and set alpha to 1
//            holder.itemView.clearAnimation();
//            holder.itemView.setAlpha(1.0f);
//        }
//
//        // Update last position
//        lastPosition = holder.getAdapterPosition();

    }


    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }

    class PlaceListViewHolder extends RecyclerView.ViewHolder{

        public PlaceOnVisitlistFragmentBinding binding;

        public PlaceListViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = PlaceOnVisitlistFragmentBinding.bind(itemView);
        }

    }

}
