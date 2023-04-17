package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.LoginFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    private LoginFragmentBinding mBinding;
    public final static String fragmentName = "loginFragment";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mBinding = LoginFragmentBinding.inflate(inflater, container, false);

        mBinding.regButton.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
        });


        mAuth = FirebaseAuth.getInstance();
        mBinding.loginButton.setOnClickListener(view ->{

            if (mBinding.loginEdittext.getText().toString().isEmpty() || mBinding.passwordEdittext.getText().toString().isEmpty()){
                Log.d("TakeAndGoDev_Command", "Empty");
            } else{
                Log.d("TakeAndGoDev_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
                mAuth
                    .signInWithEmailAndPassword(mBinding.loginEdittext.getText().toString().trim(), mBinding.passwordEdittext.getText().toString().trim())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
//                            NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.mainScreenFragment, false).build();
//                            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_main_graph, null, navOptions);
                            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainScreenFragment);
                            Log.d("TakeAndGoDev_Command_UID",  mAuth.getUid());
                        } else{
                            Log.d("TakeAndGoDev_Command", "Auth doesn't done!");
                        }
                    });
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
