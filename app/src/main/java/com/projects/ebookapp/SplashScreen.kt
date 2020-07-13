package com.projects.ebookapp

import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.projects.ebookapp.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    private val mHandler = Handler()
    private val mLauncher = Launcher()
    override fun onStart() {
        super.onStart()
        mHandler.postDelayed(mLauncher, SPLASH_DELAY_TIME.toLong())
    }

    override fun onStop() {
        mHandler.removeCallbacks(mLauncher)
        super.onStop()
    }

    private inner class Launcher : Runnable {
        override fun run() {
            launch()
        }

        private fun launch() {
            if (!isFinishing) {
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        private const val SPLASH_DELAY_TIME = 950
    }
}