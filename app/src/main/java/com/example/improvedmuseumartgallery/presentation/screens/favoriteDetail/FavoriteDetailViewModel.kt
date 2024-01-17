package com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.improvedmuseumartgallery.domain.useCase.GetFavoriteArtworkById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailViewModel @Inject constructor(
    private val getFavoriteArtworkByIdUseCase: GetFavoriteArtworkById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    @OptIn(ExperimentalCoroutinesApi::class)
    val favoriteArtwork = savedStateHandle.getStateFlow<String?>("artId", null)
        .flatMapLatest { artId ->
            getFavoriteArtworkByIdUseCase(artId?.toIntOrNull() ?: 0)
        }


}
