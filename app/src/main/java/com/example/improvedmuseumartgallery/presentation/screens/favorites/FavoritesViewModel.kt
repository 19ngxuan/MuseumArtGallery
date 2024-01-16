package com.example.improvedmuseumartgallery.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.domain.useCase.GetAllFavoriteArtworkIds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getAllFavoriteArtworkIdsUseCase: GetAllFavoriteArtworkIds
) :
    ViewModel() {


    val favoriteArtworkIdListFlow = getAllFavoriteArtworkIdsUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )


}






