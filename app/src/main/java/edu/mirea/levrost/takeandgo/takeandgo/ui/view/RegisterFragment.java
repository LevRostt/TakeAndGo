package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.RegisterFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class RegisterFragment extends Fragment {
    private RegisterFragmentBinding mBinding;
    private UserViewModel mViewModel;
    public final static String fragmentName = "registerFragment";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mBinding = RegisterFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mAuth = FirebaseAuth.getInstance();

        mBinding.regButton.setOnClickListener(view ->{
            tryRegister();
        });

        return mBinding.getRoot();
    }

    private void tryRegister(){

        if (mBinding.loginEdittext.getText().toString().isEmpty() || mBinding.passwordEdittext.getText().toString().isEmpty()){
            Log.d("TakeAndGoDev", "Empty");
        } else{
            Log.d("TakeAndGoDev_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
            mAuth.createUserWithEmailAndPassword(mBinding.loginEdittext.getText().toString(), mBinding.passwordEdittext.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_mainScreenFragment);
                            Log.d("TakeAndGoDev_Command", "Adding is done!");

                            mViewModel.insertData(new UserData("Test name", mAuth.getUid(), Arrays.asList(1L, 4L))); // Тут нужно будет парсить значения имени и вставлять

                            getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE)
                                    .edit()
                                    .putString("id", mAuth.getUid())
                                    .apply();
                        }
                        else{
                            Log.d("TakeAndGoDev_Command", "Adding doesn't done!");
                        }
                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
