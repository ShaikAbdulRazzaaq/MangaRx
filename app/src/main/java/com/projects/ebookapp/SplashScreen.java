package com.projects.ebookapp;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.projects.ebookapp.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DELAY_TIME = 950;
    private final Handler mHandler = new Handler();
    private final Launcher mLauncher = new Launcher();

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mLauncher, SPLASH_DELAY_TIME);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mLauncher);
        super.onStop();
    }

    private class Launcher implements Runnable {
        @Override
        public void run() {
            launch();
        }

        private void launch() {
            if (!isFinishing()) {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }
    }
}