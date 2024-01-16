package com.example.improvedmuseumartgallery.presentation.screens

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data object Error : UiState<Nothing>
    data object Loading : UiState<Nothing>
}