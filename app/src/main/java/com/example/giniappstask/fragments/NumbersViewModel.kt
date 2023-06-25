package com.example.giniappstask.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giniappstask.data.NumbersUiState
import com.example.giniappstask.repository.NumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(private val repository: NumbersRepository) : ViewModel() {

    private val _numbersUiState = MutableStateFlow<NumbersUiState>(NumbersUiState.Loading(true))
    val numbersUiState: StateFlow<NumbersUiState> get() = _numbersUiState

    init {
        viewModelScope.launch {
            delay(3000L)
            try {
                _numbersUiState.value = NumbersUiState.Loading(true)
                val result = repository.getNumbers()
                val list = result.body()?.numbers?.map { it.number }?.sorted()!!
                _numbersUiState.value = NumbersUiState.Success(list)
            } catch (e: Exception) {
                _numbersUiState.value = NumbersUiState.Error(e)
            }
            finally {
                _numbersUiState.value = NumbersUiState.Loading(false)
            }
        }
    }
 }
