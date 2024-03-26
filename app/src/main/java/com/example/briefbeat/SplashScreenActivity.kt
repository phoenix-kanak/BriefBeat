package com.example.briefbeat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.PrecomputedText.Params
import android.view.WindowManager
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        val icon=findViewById<ImageView>(R.id.splash_icon)
//        supportActionBar?.hide()
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        Handler().postDelayed({
            val icon=findViewById<ImageView>(R.id.splash_icon)

        icon.alpha=1f;
        icon.animate().setDuration(4000).alpha(1f).withEndAction{
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }
           // }, 2000)



    }
}