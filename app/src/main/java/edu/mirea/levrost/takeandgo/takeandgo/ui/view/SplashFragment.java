package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takeandgo.databinding.FragmentSplashBinding;

import edu.mirea.levrost.takeandgo.takeandgo.ui.view.activity.MainActivity;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding mBinding;
    private UserViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSplashBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        renderAnimations();
        mViewModel.isUserLogin().observe(getViewLifecycleOwner(), (userInfo) ->{
            launchMainScreen(userInfo.isLogin());
        });
    }

    private void launchMainScreen(boolean isLogin){
        Intent intent = new Intent(getContext(), MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("isLogin", true);
        Log.d("TakeAndGoDevInLauncher ", String.valueOf(isLogin));

        new Thread(() ->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(intent);

        }).start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


    private void renderAnimations() {
        mBinding.loadingIndicator.setAlpha(0f);
        mBinding.loadingIndicator.animate()
                .alpha(0.7f)
                .setDuration(500)
                .start();

        mBinding.pleaseWaitTextView.setAlpha(0f);
        mBinding.pleaseWaitTextView.animate()
                .alpha(1f)
                .setStartDelay(200)
                .setDuration(500)
                .start();

    }
}