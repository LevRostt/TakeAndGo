package edu.mirea.levrost.takeandgo.takeandgo.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.CommunityScreenFragment;
import edu.mirea.levrost.takeandgo.takeandgo.ui.view.CommunityScreenFragmentDirections;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.ProfileOnCommunityScreenFragmentBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProfileListRVAdapter extends RecyclerView.Adapter<ProfileListRVAdapter.ProfileViewHolder> {
    List<Profile> data;
    private CommunityScreenFragment fragment;

    public ProfileListRVAdapter(CommunityScreenFragment fragment){
        this.data = new ArrayList<>();
        this.fragment = fragment;
    }

    public ProfileListRVAdapter(List<Profile> data){ this.data = data; }

    public void updateData(List<Profile> newData) {
        newData.sort((o1, o2) -> {
            if (o1.getRating() < o2.getRating()){
                return 1;
            }
            return -1;
        });

        data = newData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(
                ProfileOnCommunityScreenFragmentBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent,false)
                        .getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        int resId = context.getResources().getIdentifier(data.get(position).getIcon(), "drawable", context.getPackageName());
        holder.binding.profileIcon.setImageResource(resId);
        holder.binding.profileName.setText(data.get(position).getName());
        holder.binding.profileRating.setText(String.valueOf(data.get(position).getRating()));
        holder.binding.profileId.setText(String.valueOf(data.get(position).getId()));
        holder.binding.profileIcon.setClipToOutline(true);
        holder.binding.profileIcon.setCropToPadding(true);

        holder.binding.showButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment).navigate(
                    CommunityScreenFragmentDirections
                            .actionCommunityScreenFragmentToProfileScreenFragment(data.get(position).getId())
                            .setIsFriend(fragment.getArguments().getBoolean("isFriend")));
        });

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

    class ProfileViewHolder extends RecyclerView.ViewHolder{

        public ProfileOnCommunityScreenFragmentBinding binding;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ProfileOnCommunityScreenFragmentBinding.bind(itemView);
        }

    }

}
