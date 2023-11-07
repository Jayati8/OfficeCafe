package com.example.officecafe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
//    private var receivedEmail:String? = null
//    private var token:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val shrd = sharedPreferences.getString("token",null)
        val email = sharedPreferences.getString("email",null)
        Log.d("preference","$email")

        if (shrd?.isNotEmpty() == true) {
            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("token", shrd)
                intent.putExtra("email",email)

            startActivity(intent)
            finish()
        }, 3000)
        }
        else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}