package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.RegisterFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {
    private RegisterFragmentBinding mBinding;
    public final static String fragmentName = "registerFragment";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mBinding = RegisterFragmentBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();
        mBinding.regButton.setOnClickListener(view ->{

            if (mBinding.loginEdittext.getText().toString().isEmpty() || mBinding.passwordEdittext.getText().toString().isEmpty()){
                Log.d("TakeAndGoDev", "Empty");
            } else{
                Log.d("TakeAndGoDev_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
                mAuth.createUserWithEmailAndPassword(mBinding.loginEdittext.getText().toString(), mBinding.passwordEdittext.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_mainScreenFragment);
                                Log.d("TakeAndGoDev_Command", "Adding is done!");
                                Log.d("TakeAndGoDev_UID",  mAuth.getUid());
                            }
                            else{
                                Log.d("TakeAndGoDev_Command", "Adding doesn't done!");
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
