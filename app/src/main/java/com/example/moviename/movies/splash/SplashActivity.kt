package com.example.moviename.movies.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.moviename.R
import com.example.moviename.movies.movies.MainActivity

/**
 * Splash activity for this application.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initializeViews()

    }

    /**
     * this method is used to put the delay for 3 seconds after we will open the main activity.
     */
    private fun initializeViews() {
        Handler().postDelayed({
            /* Create an Intent that will start the Main-Activity. */
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)
    }
}
