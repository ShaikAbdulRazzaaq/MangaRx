package com.projects.ebookapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.projects.ebookapp.fragments.CategoriesFragment
import com.projects.ebookapp.fragments.FavoriteFragment
import com.projects.ebookapp.fragments.HomePageFragment

class MainActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationBar)
        bottomNavigationView?.menu?.findItem(R.id.home)?.isChecked = true
        loadFragment(HomePageFragment())
        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener
        setOnNavigationItemSelectedListener@{ item: MenuItem ->
            val fragment: Fragment = when (item.itemId) {
                R.id.categories -> categoriesFragment
                R.id.favorites -> favoriteFragment
                R.id.home -> homeFragment
                else -> return@setOnNavigationItemSelectedListener false
            }
            loadFragment(fragment)
        })

    }

    private val categoriesFragment: Fragment
        get() {
            val fragment: Fragment
            fragment = CategoriesFragment()
            Log.d(TAG, "onCreate: Categories")
            return fragment
        }

    private val favoriteFragment: Fragment
        get() {
            val fragment: Fragment
            fragment = FavoriteFragment()
            Log.d(TAG, "onCreate: Favorites")
            return fragment
        }
    private val homeFragment: Fragment
        get() {
            val fragment: Fragment
            fragment = HomePageFragment()
            Log.d(TAG, "onCreate: Home")
            return fragment
        }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, fragment).commit()
            return true
        }
        return false
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}