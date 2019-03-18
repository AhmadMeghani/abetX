package com.example.abetx.Login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.abetx.R;
import com.example.abetx.Utilities.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword, mUsername;
    private String email, password, username;
    private Button btnRegistering;

    private FirebaseMethods firebaseMethods;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        firebaseMethods = new FirebaseMethods(Register.this);
        initWidgets();
        init();
        setupFireBaseAuth();
    }

    private void init() {
        btnRegistering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                username = mUsername.getText().toString();
                if (checkInputs(email, password, username)) {
                    mProgressBar.setVisibility(View.VISIBLE);

                    firebaseMethods.registerNewUser(email, password, username);
                }
            }
        });
    }
    private void initWidgets() {
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mUsername = (EditText) findViewById(R.id.input_username);
        btnRegistering = (Button) findViewById(R.id.bt_register);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    private boolean checkInputs(String email, String password, String username) {
        if (email.equals("") || password.equals("") || username.equals("")) {
            return false;
        }
        return true;
    }

    //------------------------------FireBase-------------------------------------

    private void setupFireBaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Toast.makeText(RegisterActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                    //  Toast.makeText(MainActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

