package com.projects.ebookapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projects.ebookapp.fragments.CategoriesFragment;
import com.projects.ebookapp.fragments.FavoriteFragment;
import com.projects.ebookapp.fragments.HomePageFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        loadFragment(new HomePageFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.categories:
                    fragment = getCategoriesFragment();
                    break;
                case R.id.favorites:
                    fragment = getFavoriteFragment();
                    break;
                case R.id.home:
                    fragment = getHomeFragment();
                    break;
                default:
                    return false;
            }
            return loadFragment(fragment);
        });
    }

    private Fragment getCategoriesFragment() {
        Fragment fragment;
        fragment = new CategoriesFragment();
        Log.d(TAG, "onCreate: Categories");
        return fragment;
    }

    private Fragment getFavoriteFragment() {
        Fragment fragment;
        fragment = new FavoriteFragment();
        Log.d(TAG, "onCreate: Favorites");
        return fragment;
    }

    private Fragment getHomeFragment() {
        Fragment fragment;
        fragment = new HomePageFragment();
        Log.d(TAG, "onCreate: Home");
        return fragment;
    }

    boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.fragmentHolder, fragment).commit();
            return true;
        }
        return false;
    }
}