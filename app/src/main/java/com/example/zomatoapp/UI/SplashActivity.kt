package com.example.zomatoapp.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.zomatoapp.ContinueWithGoogle
import com.example.zomatoapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val i = Intent(this, ContinueWithGoogle::class.java)
            startActivity(i)

            finish()
        }, 3000)
    }
}