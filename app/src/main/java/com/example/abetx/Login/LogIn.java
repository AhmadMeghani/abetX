package com.example.abetx.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abetx.MainActivity;
import com.example.abetx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        setupFireBaseAuth();
        init();
    }

    private boolean isStringNull(String stg) {
        if (stg.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    //---------------------------FireBase--------------------------------
    private void init() {
        Button button = (Button) findViewById(R.id.bt_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = mEmail.getText().toString();
                String Password = mPassword.getText().toString();
                if (!isStringNull(Email)) {
                    if (!isStringNull(Password)) {
                        //mProgressBar.setVisibility(View.VISIBLE);
                        //loggingIn.setVisibility(View.VISIBLE);

                        mAuth.signInWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Toast.makeText(LogIn.this, "Success Full",
                                                    Toast.LENGTH_SHORT).show();

                                            try {
                                                if (user.isEmailVerified()) {
                                                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    //mProgressBar.setVisibility(View.GONE);
                                                    //loggingIn.setVisibility(View.GONE);
                                                    Toast.makeText(LogIn.this, "Email is Not Verified!\n Check Your Mail Box", Toast.LENGTH_SHORT).show();
                                                    mAuth.signOut();
                                                }

                                            } catch (NullPointerException e) {

                                            }
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(LogIn.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            //mProgressBar.setVisibility(View.GONE);
                                            //loggingIn.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(LogIn.this, "Add Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogIn.this, "Email Is Missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView linkSignUp = (TextView) findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });
        if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()) {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupFireBaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
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
