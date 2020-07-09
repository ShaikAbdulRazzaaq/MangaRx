package com.projects.ebookapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projects.ebookapp.MainActivity;
import com.projects.ebookapp.R;

import java.util.Objects;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    EditText name;
    EditText loginPassword;
    Button login;
    Button signup;
    CoordinatorLayout coordinatorLayout;
    ProgressBar progressBar;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        loginPassword = findViewById(R.id.loginPassword);
        name = findViewById(R.id.et_yourName);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signUp);
        progressBar = findViewById(R.id.loginProgress);
        coordinatorLayout = findViewById(R.id.coordinatorLoginPage);
        login.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            validateCredentials(name.getText().toString(), loginPassword.getText().toString());
        });
        signup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
    }

    private void validateCredentials(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(username))
                name.setError(getString(R.string.required_field));
            if (TextUtils.isEmpty(password))
                loginPassword.setError(getString(R.string.required_field));
        } else {
            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    firebaseUser = firebaseAuth.getCurrentUser();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Snackbar.make(coordinatorLayout, Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()), LENGTH_LONG).show();
                    Log.e(TAG, "validateCredentials: ", task.getException());
                }
            });
        }
    }

}