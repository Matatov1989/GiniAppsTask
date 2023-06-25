package com.example.giniappstask.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giniappstask.repository.NumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(private val repository: NumbersRepository) :
    ViewModel() {

    val numbersLiveData = MutableLiveData<List<Int>>()

    private val numbers: Flow<List<Int>> = flow {
        val response = repository.getNumbers()
        if (response.isSuccessful)
            emit(response.body()?.numbers?.map { it.number }?.sorted()!!)
        else
            Log.e("ERROR", "response is ${response.isSuccessful}")
    }

    fun getNumbers() {
        viewModelScope.launch(Dispatchers.IO) {
            numbers.collect { number ->
                numbersLiveData.postValue(number)
            }
        }
    }
 }
