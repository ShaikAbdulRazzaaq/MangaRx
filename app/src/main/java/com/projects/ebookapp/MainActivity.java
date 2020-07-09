package com.projects.ebookapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new CategoriesFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.categories: {
                    fragment = new CategoriesFragment();
                    Log.d(TAG, "onCreate: Categories");
                    break;
                }
                case R.id.favorites: {
                    fragment = new FavoriteFragment();
                    Log.d(TAG, "onCreate: Favorites");
                    break;
                }
                case R.id.home: {
                    fragment = new HomePageFragment();
                    Log.d(TAG, "onCreate: Home");
                    break;
                }
            }
            return loadFragment(fragment);
        });
      /*  bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
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
        });*/
    }

    boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.fragmentHolder, fragment).commit();
            return true;
        }
        return false;
    }
}