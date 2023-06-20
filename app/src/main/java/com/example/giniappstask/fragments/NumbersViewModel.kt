package com.example.giniappstask.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giniappstask.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumbersViewModel : ViewModel(){

    val numbersLiveData = MutableLiveData<List<Int>>()

    fun fetchNumbers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.numberService.getNumbers()
                if (response.isSuccessful) {
                    val numberResponse = response.body()
                    val sortedNumbers = numberResponse?.numbers?.map { it.number }?.sorted()
                    numbersLiveData.postValue(sortedNumbers)
                } else {
                    Log.e("ERROR", "${response.isSuccessful}")
                }
            } catch (e: Exception) {
                Log.e("ERROR", "${e.message}")
            }
        }
    }
}
