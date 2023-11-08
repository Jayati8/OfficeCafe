package com.example.officecafe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.officecafe.databinding.ActivityDetailedBinding
import com.example.officecafe.network.KotlinClient
import kotlinx.coroutines.launch

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private var receivedEmail:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        receivedEmail = intent.getStringExtra("email")
        val view = binding.root
        setContentView(view)
        getData()
        onClick()
    }

    private fun onClick() {
      binding.creditNote.setOnClickListener {
          startCreditNoteActivity()
      }
    }

    private fun getData() {

        val requestClass = GetBalanceRequest(receivedEmail!!)
        val loginApiService = KotlinClient.retrofit

        lifecycleScope.launch {
            try {
               Log.d("DetailActivity", "request: $requestClass")
                val response = loginApiService.requestGetBalance(requestClass)
                Log.d("DetailActivity", "getData: $response")
                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null) {
                        binding.Name.text = responseData.data.name
                        binding.Email.text = responseData.data.email
                        binding.balanceValue.text = responseData.data.balance.toString()

                        Log.e("DetailActivity", "$responseData")
                    } else {
                        Log.e("DetailActivity", "Response body is null")
                    }
                } else {
                    Log.e("DetailActivity", "Unsuccessful response: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("DetailActivity", "Error: ${e.message}", e)
            }
        }
    }

    private fun startCreditNoteActivity() {

        val intent = Intent(this,CreditNoteActivity::class.java)
        intent.putExtra("email",receivedEmail)
        startActivity(intent)
        finish()
    }
}


