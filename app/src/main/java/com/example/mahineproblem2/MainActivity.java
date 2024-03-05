package com.example.mahineproblem2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogFragment loginFragment = new LoginDialogFragment();
        loginFragment.show(getFragmentManager(), "login");

        context = this;
    }

    @SuppressLint("ValidFragment")
    public class LoginDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.layout_login, null);
            EditText etUsername = (EditText) v.findViewById(R.id.username);
            EditText etPassword = (EditText) v.findViewById(R.id.password);
            builder.setView(v)
                    .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
//
//                            String username, password;
                            String username = etUsername.getText().toString();
                            String password = etPassword.getText().toString();
                            String msg;
                            if(username.equals("abcd") && password.equals("1234")) {
                                msg = "Login Successful";

                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                msg = "Access Denied!";

                                DialogFragment loginFragment = new LoginDialogFragment();
                                loginFragment.show(getFragmentManager(), "login");
                            }
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            return builder.create();
        }
    }

}