package com.example.improvedmuseumartgallery.presentation.screens.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.useCase.DeleteFavoriteArt
import com.example.improvedmuseumartgallery.domain.useCase.GetArtwork
import com.example.improvedmuseumartgallery.domain.useCase.GetFavoriteArtworkById
import com.example.improvedmuseumartgallery.domain.useCase.InsertFavoriteArt
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArtworkUseCase: GetArtwork,
    private val insertFavoriteArtUseCase: InsertFavoriteArt,
    private val deleteFavoriteArtUseCase: DeleteFavoriteArt,
    private val getFavoriteArtworkByIdUseCase: GetFavoriteArtworkById,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val artwork = savedStateHandle.getStateFlow<String?>("artId", null)
        .map { artId ->
            getArtworks(artId?.toIntOrNull() ?: 0)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val isBookmarked: Flow<Boolean> =
        savedStateHandle.getStateFlow<String?>("artId", null)
            .flatMapLatest {
                getFavoriteArtworkByIdUseCase(it?.toIntOrNull() ?: 0)
            }.map { artwork -> artwork != null }

    val detailUiState = combineTransform(artwork, isBookmarked) { artwork, isBookmarked ->
        emit(UiState.Loading)
        if (artwork != null) {
            val newUiState = UiState.Success(DetailUIModel(artwork, isBookmarked))
            emit(newUiState)
        } else {
            emit(UiState.Error)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)


    private suspend fun getArtworks(artId: Int): Artwork? {
        return try {
            getArtworkUseCase(artId)
        } catch (e: Exception) {
            null
        }
    }

    fun setBookmark(isBookmarked: Boolean) {
        val id = savedStateHandle.get<String>("artId")?.toIntOrNull() ?: 0
        viewModelScope.launch {
            val favoriteArtworkInDb = getFavoriteArtworkByIdUseCase(id).firstOrNull()

            if (isBookmarked && (favoriteArtworkInDb == null)) {
                saveFavoriteArt()
                Log.d("ViewModel", "Saving artwork to favorites.")

            } else if (!isBookmarked && (favoriteArtworkInDb != null)) {
                Log.d("ViewModel", "Removing artwork from favorites.")
                deleteFavoriteArt()
            }

        }
    }

    private suspend fun saveFavoriteArt() {
        val artworkUiState = detailUiState.value
        if (artworkUiState is UiState.Success) {
            val favoriteArtwork = artworkUiState.data.artwork.toFavoriteArtwork()
            insertFavoriteArtUseCase(favoriteArtwork)
        }
    }

    private suspend fun deleteFavoriteArt() {
        val artworkUiState = detailUiState.value
        if (artworkUiState is UiState.Success) {
            val favoriteArtwork = artworkUiState.data.artwork.toFavoriteArtwork()
            deleteFavoriteArtUseCase(favoriteArtwork)

        }
    }

}

fun Artwork.toFavoriteArtwork(): FavoriteArtwork {
    return FavoriteArtwork(
        objectID = objectID,
        title = title,
        artistDisplayName = artistDisplayName,
        department = department,
        primaryImage = primaryImage
    )
}