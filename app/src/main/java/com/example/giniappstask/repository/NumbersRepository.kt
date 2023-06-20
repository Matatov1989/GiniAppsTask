package com.example.giniappstask.repository

import com.example.giniappstask.models.NumberResponse
import com.example.giniappstask.network.NumbersApi
import retrofit2.Response
import javax.inject.Inject

class NumbersRepository @Inject constructor(private val api: NumbersApi) {

    suspend fun getNumbers(): Response<NumberResponse> {
        return api.getNumbers()
    }
}
