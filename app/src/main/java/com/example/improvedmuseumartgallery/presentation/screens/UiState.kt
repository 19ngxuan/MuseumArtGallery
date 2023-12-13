package com.example.improvedmuseumartgallery.presentation.screens

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    object Error : UiState<Nothing>
    object Loading : UiState<Nothing>
}