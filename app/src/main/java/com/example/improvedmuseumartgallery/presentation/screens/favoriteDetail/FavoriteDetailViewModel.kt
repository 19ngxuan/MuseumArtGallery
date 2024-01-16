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

//    //TODO convert to reactive approach
//    //TODO it should be state flow.
//    private val _favoriteDetailUiState = MutableStateFlow<UiState<FavoriteArtwork>>(UiState.Loading)
//    val favoriteDetailUiState: StateFlow<UiState<FavoriteArtwork>> =
//        _favoriteDetailUiState.asStateFlow()
//
//    //TODO after reactive approach we don't need it anymore, remove init block
//    init {
//        val artIdString = savedStateHandle.get<String>("artId")
//        val artId = artIdString?.toIntOrNull()
//        artId?.let { id ->
//            viewModelScope.launch {
//                getFavoriteArtworkByIdUseCase(id).collect { result ->
//                    if (result != null) {
//                        _favoriteDetailUiState.value = UiState.Success(result)
//                    }
//                }
//            }
//        } ?: run {
//            _favoriteDetailUiState.value = UiState.Error
//        }
//    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val favoriteArtwork = savedStateHandle.getStateFlow<String?>("artId", null)
        .flatMapLatest { artId ->
            getFavoriteArtworkByIdUseCase(artId?.toIntOrNull() ?: 0)
        }


}
