package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.databinding.ModerationChatsFragmentBinding;

public class ModerationChatsFragment extends Fragment {

    private ModerationChatsFragmentBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = ModerationChatsFragmentBinding.inflate(inflater, container, false);
        mBinding.addPlaceBtn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(ModerationChatsFragmentDirections.actionModerationChatsFragmentToModerationMessegeFragment());
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
