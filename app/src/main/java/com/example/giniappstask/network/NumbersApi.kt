package com.example.giniappstask.network

import com.example.giniappstask.models.NumberResponse
import retrofit2.Response
import retrofit2.http.GET

interface NumbersApi {

    @GET("raw/8wJzytQX")
    suspend fun getNumbers(): Response<NumberResponse>
}
