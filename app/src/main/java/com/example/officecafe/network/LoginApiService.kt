package com.example.officecafe.network

import android.util.Log
import com.example.officecafe.CreditCashRequest
import com.example.officecafe.CreditNoteResponse
import com.example.officecafe.GetBalanceRequest
import com.example.officecafe.GetBalanceResponse
import com.example.officecafe.HistoryRequest
import com.example.officecafe.LoginResponse
import com.example.officecafe.OTPResponse
import com.example.officecafe.PaymentDataResponse
import com.example.officecafe.RequestModel
import com.example.officecafe.RequestModelOtp
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


private const val BASE_URL = "http://canteen.cynoteck.org/api/"

object KotlinClient
{
    var authToken:String? = null


    private val authInterceptor = Interceptor{chain ->

        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization", "Bearer $authToken")
                .build()
            Log.d("KotlinClient","$authToken")
        }

        chain.proceed(req)
    }
    var gson = GsonBuilder()
        .setLenient()
        .create()




    private val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS)

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

     val retrofit: LoginApiService = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(LoginApiService::class.java)
}

interface LoginApiService {
    @POST("moblogin")
    suspend fun requestLogin(@Body requestModel: RequestModel) : Response<LoginResponse>

    @POST("sendotp")
    suspend fun requestLoginOtp(@Body requestModel: RequestModelOtp) : Response<OTPResponse>

    @POST("getbalance")
    suspend fun requestGetBalance( @Body requestModel: GetBalanceRequest): Response<GetBalanceResponse>

    @POST("creditNoteApi")
    suspend fun requestCreditNote( @Body requestModel: CreditCashRequest): Response<CreditNoteResponse>

    @POST("paymenthistory")
    suspend fun requestPaymentHistory( @Body requestModel: HistoryRequest): Response<PaymentDataResponse>
}

/*object LoginApi {
    val retrofitService: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java) }
}*/
