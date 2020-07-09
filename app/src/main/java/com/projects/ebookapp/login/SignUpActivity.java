package com.projects.ebookapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.projects.ebookapp.R;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText username;
    EditText password;
    Button signUp;
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.et_howCanICallYou);
        password = findViewById(R.id.et_password);
        signUp = findViewById(R.id.btn_signUpToLogin);
        signUp.setOnClickListener(v -> validateCredentials(username.getText().toString(), password.getText().toString()));
    }

    private void validateCredentials(String userName, String passWord) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            if (TextUtils.isEmpty(userName)) username.setError(getString(R.string.required_field));
            if (TextUtils.isEmpty(passWord)) password.setError(getString(R.string.required_field));
        } else {
            firebaseAuth.createUserWithEmailAndPassword(userName, passWord).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Signed Up successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                } else {
                    Log.e(TAG, "validateCredentials: ", task.getException());
                }
            });
        }
    }
}