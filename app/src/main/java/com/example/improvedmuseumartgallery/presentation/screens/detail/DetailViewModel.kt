package com.example.improvedmuseumartgallery.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.useCase.GetArtwork
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


//TODO get artId as constructor param (Hilt should provide a way to that)
// HiltViewModel does not work with AssistedInjection
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArtworkUseCase: GetArtwork,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val detailUiState = savedStateHandle.getStateFlow<String?>("artId", null).transform { artId ->
        if (artId != null) {
            emit(UiState.Loading)
            val result = getArtworks(artId.toIntOrNull()?:0)
            emit(result)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)


    //TODO send loading state first

    //TODO send success or error state

    // TODO convert it to stateFlow
    private suspend fun getArtworks(artId: Int): UiState<Artwork> {
        return try {
            val result = getArtworkUseCase(artId)
            UiState.Success(result)
        } catch (e: Exception) {
            UiState.Error
        }
    }
}
