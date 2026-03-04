package eu.tvato.lempie.api

import android.util.Log
import eu.tvato.lempie.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var baseUrl = Constants.BASE_URL

    // Start of logging stuff
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    // End of logging stuff


    fun setBaseURL(url: String){
        baseUrl = url
    }

    private val retrofit by lazy {
        Log.d("dd", "retrofit with baseUrl: $baseUrl")
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: API by lazy {
        retrofit.create(API::class.java)
    }
}