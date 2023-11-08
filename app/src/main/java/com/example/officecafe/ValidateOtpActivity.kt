package com.example.officecafe

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.officecafe.databinding.ActivityValidateOtpBinding
import com.example.officecafe.network.KotlinClient
import kotlinx.coroutines.launch

class ValidateOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidateOtpBinding
    private var receivedEmail:String? = null
    private var token:String? =null

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidateOtpBinding.inflate(layoutInflater)
        receivedEmail = intent.getStringExtra("email")
        sharedPreferences= getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val view = binding.root
        setContentView(view)
        onClick()
    }

    private fun onClick() {
        binding.validate.setOnClickListener{

            val requestClass = RequestModelOtp(receivedEmail,binding.pinview.text.toString().toInt())
            val loginApiService =KotlinClient.retrofit

            lifecycleScope.launch {
                try {
                    val response = loginApiService.requestLoginOtp(requestClass)
                    if (response.isSuccessful) {
                        KotlinClient.authToken = response.body()?.data?.token
                        val responseData = response.body()
                        if (responseData != null) {
                            token = response.body()!!.data.token
                            Log.d("preference", response.body()!!.data.email)
                            receivedEmail = response.body()!!.data.email

                            // Save the token to SharedPreferences
                            editor.putString("token", token)
                            editor.putString("email",receivedEmail)

                            editor.commit()
                            startDetailedActivity()
                        } else {
                            Log.e("LoginOtp", "Response body is null")
                        }
                    } else {
                        Log.e("LoginOtp", "Unsuccessful response: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.e("LoginOtp", "Error: ${e.message}", e)
                }
            }
        }
    }

    private fun startDetailedActivity() {
        Log.d("home","success")

        val intent = Intent(this,DetailedActivity::class.java)
        intent.putExtra("email",receivedEmail)
        intent.putExtra("token",token)
        startActivity(intent)
        finish()
    }
}



