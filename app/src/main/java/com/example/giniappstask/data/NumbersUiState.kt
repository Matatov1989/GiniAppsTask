package com.example.giniappstask.data


sealed class NumbersUiState {
    data class Success(val numbers: List<Int>): NumbersUiState()
    data class Error(val exception: Exception): NumbersUiState()
    data class Loading(val isLoad: Boolean): NumbersUiState()
}
