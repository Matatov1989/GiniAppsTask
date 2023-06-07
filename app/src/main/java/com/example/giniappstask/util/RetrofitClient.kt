package com.example.giniappstask.util

import com.example.giniappstask.network.NumberService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://pastebin.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val numberService: NumberService by lazy {
        retrofit.create(NumberService::class.java)
    }
}
