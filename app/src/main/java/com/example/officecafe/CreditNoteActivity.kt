package com.example.officecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.officecafe.databinding.ActivityCreditNoteBinding
import com.example.officecafe.network.KotlinClient
import kotlinx.coroutines.launch

class CreditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreditNoteBinding
    private var receivedEmail: String? = null
    private var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditNoteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_credit_note)

        val receivedEmail = intent.getStringExtra("email")!!
        val receivedToken = intent.getStringExtra("token")!!

        val requestClass = CreditCashRequest(receivedEmail)
        val loginApiService = KotlinClient.retrofit

            lifecycleScope.launch {
                try {

                    val response = loginApiService.requestCreditNote(receivedToken, requestClass)
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (responseData != null) {

//                        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//                        recyclerView.layoutManager = LinearLayoutManager(this@CreditNoteActivity)
//                        val creditNote = CreditNoteResponse
//                        val adapter = CreditNoteAdapter(creditNote)
//                        recyclerView.adapter = adapter

                            Log.e("CreditNoteActivity", "${responseData.response}")
                        } else {
                            Log.e("CreditNoteActivity", "Response body is null")
                        }
                    } else {
                        Log.e("CreditNoteActivity", "Unsuccessful response: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.e("CreditNoteActivity", "Error: ${e.message}", e)
                }
            }
        }
    }