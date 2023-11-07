package com.example.officecafe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.officecafe.databinding.ActivityMainBinding
import com.example.officecafe.network.KotlinClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var email:String?=null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        onClick()

        val emailAddressEditText = binding.EmailAddress
        val signInButton = binding.signIn

        emailAddressEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Before text changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Text is changing
            }

            override fun afterTextChanged(s: Editable?) {
                // After text changed

                // Validate the email address
                val emailText = s.toString()
                if (isValidEmail(emailText)) {
                    // Email address is valid
                    emailAddressEditText.error = null // Clear any previous error
                    signInButton.isEnabled = emailText.isNotBlank()
                } else {
                    // Email address is not valid, show an error
                    emailAddressEditText.error = "Invalid email address"
                    signInButton.isEnabled = false
                }
            }
        })
    }

    private fun onClick() {

        binding.signIn.setOnClickListener{

            email = binding.EmailAddress.text.toString()
            val requestModel = RequestModel(email!!)
            val loginApiService = KotlinClient.api

            lifecycleScope.launch {
                try {
                    val response = loginApiService.requestLogin(requestModel)
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (responseData?.status == 200) {
                            Log.e("Login", "200")
                            startOtpValidateActivity()

                        } else {
                            Log.e("Login", "Response body is null")
                        }
                    } else {
                        Log.e("Login", "Unsuccessful response: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("Login", "Error: ${e.message}", e)
                }
            }
        }
    }

    private fun startOtpValidateActivity() {
        val intent = Intent(this,ValidateOtpActivity::class.java)
        intent.putExtra("email",email)
        startActivity(intent)
        finish()
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@cynoteck.com"
    return email.matches(emailPattern.toRegex())
}





