package com.example.takeandgo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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
                Log.d("My_Auth_Command", "Empty");
            } else{
                Log.d("My_Auth_Command_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
                mAuth.createUserWithEmailAndPassword(mBinding.loginEdittext.getText().toString(), mBinding.passwordEdittext.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Log.d("My_Auth_Command", "Adding is done!");
                                Log.d("My_Auth_Command_UID",  mAuth.getUid());
                            }
                            else{
                                Log.d("My_Auth_Command", "Adding doesn't done!");
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
