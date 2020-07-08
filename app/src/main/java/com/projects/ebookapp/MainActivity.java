package com.projects.ebookapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.categories: {
                    Log.d(TAG, "onCreate: Categories");
                    break;
                }
                case R.id.favorites: {
                    Log.d(TAG, "onCreate: Favorites");
                    break;
                }
                case R.id.home: {
                    Log.d(TAG, "onCreate: Home");
                    break;
                }
            }
            return false;
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.categories: {
                    Log.d(TAG, "onCreate: On Reselect Categories");
                    break;
                }
                case R.id.favorites: {
                    Log.d(TAG, "onCreate: On Reselect Favorites");
                    break;
                }
                case R.id.home: {
                    Log.d(TAG, "onCreate: On Reselect Home");
                    break;
                }
            }
        });
    }
}