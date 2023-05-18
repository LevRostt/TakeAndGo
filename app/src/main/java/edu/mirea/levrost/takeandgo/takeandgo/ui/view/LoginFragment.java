package edu.mirea.levrost.takeandgo.takeandgo.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.takeandgo.R;
import com.example.takeandgo.databinding.LoginFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;
import edu.mirea.levrost.takeandgo.takeandgo.ui.viewModel.UserViewModel;


public class LoginFragment extends Fragment {
    private LoginFragmentBinding mBinding;
    private UserViewModel mViewModel;
    //public final static String fragmentName = "loginFragment";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mBinding = LoginFragmentBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        mViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        mBinding.regButton.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
        });

        mBinding.loginButton.setOnClickListener(view -> {
            tryLogin(false);
        });

        mBinding.testUserButton.setOnClickListener(view ->{
//            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainScreenFragment);
//            Log.d("TakeAndGoDev_Command_UID",  String.valueOf(1));
//            getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE)
//                    .edit()
//                    .putString("id", String.valueOf(1))
//                    .apply();

//            mAuth.signInWithEmailAndPassword("root@ro.ot", "roottoor");
            tryLogin(true);

//            mViewModel.insertData(new UserData("Test name", String.valueOf(1), Arrays.asList())); // Тут нужно будет парсить значения имени и вставлять

        });

        return mBinding.getRoot();
    }


    private void tryLogin(boolean isTest){
        if (isTest){
            mAuth.signInWithEmailAndPassword("root@ro.ot", "roottoor")
                    .addOnCompleteListener( task -> {
                        if (task.isSuccessful()) {
                            Log.d("TakeAndGoDev_Command_UID", mAuth.getUid());
                            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainScreenFragment);
                            getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE)
                                    .edit()
                                    .putString("id", mAuth.getUid())
                                    .apply();

                            mViewModel.insertData(new UserData("User Test", mAuth.getUid())); // Тут нужно будет парсить значения имени и вставлять

                        } else {
                            mBinding.loginWarning.setText("Пожалуйста, включите интернет, чтобы войти в профиль");
                            mBinding.loginWarning.setVisibility(View.VISIBLE);
                            Log.d("TakeAndGoDev_Command", "Auth doesn't done!");
                        }
                    });
        }
        else {
            if (mBinding.loginEdittext.getText().toString().isEmpty() || mBinding.passwordEdittext.getText().toString().isEmpty()) {
                mBinding.loginWarning.setText("Введите логин и пароль");
                mBinding.loginWarning.setVisibility(View.VISIBLE);
                Log.d("TakeAndGoDev_Command", "Empty");
            } else {
                Log.d("TakeAndGoDev_text", mBinding.loginEdittext.getText().toString() + " " + mBinding.passwordEdittext.getText().toString());
                mAuth.signInWithEmailAndPassword(mBinding.loginEdittext.getText().toString().trim(), mBinding.passwordEdittext.getText().toString().trim())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d("TakeAndGoDev_Command_UID", mAuth.getUid());
                                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainScreenFragment);
                                getActivity().getSharedPreferences("UID", Context.MODE_PRIVATE)
                                        .edit()
                                        .putString("id", mAuth.getUid())
                                        .apply();

                                mViewModel.insertData(new UserData("Test name", mAuth.getUid())); // Тут нужно будет парсить значения имени и вставлять

                            } else {
                                mBinding.loginWarning.setText("Не верный логин или пароль");
                                mBinding.loginWarning.setVisibility(View.VISIBLE);
                                Log.d("TakeAndGoDev_Command", "Auth doesn't done!");
                            }
                        });
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
        mViewModel = null;
    }
}
