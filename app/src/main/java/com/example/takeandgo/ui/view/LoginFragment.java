package com.example.takeandgo.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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

            ((MainActivity) getActivity()).onNavigationFragmentSelector(RegisterFragment.fragmentName);

        });

        mAuth = FirebaseAuth.getInstance();
        mBinding.loginButton.setOnClickListener(view ->{

            if (mBinding.loginEdittext.getText().toString().isEmpty() || mBinding.passwordEdittext.getText().toString().isEmpty()){
                Log.d("My_Auth_Command", "Empty");
            } else{
                Log.d("My_Auth_Command_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
                mAuth.signInWithEmailAndPassword(mBinding.loginEdittext.getText().toString(), mBinding.passwordEdittext.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Log.d("My_Auth_Command", "Auth is done!");
                                Log.d("My_Auth_Command_UID",  mAuth.getUid());
                            }
                            else{
                                Log.d("My_Auth_Command", "Auth doesn't done!");
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
