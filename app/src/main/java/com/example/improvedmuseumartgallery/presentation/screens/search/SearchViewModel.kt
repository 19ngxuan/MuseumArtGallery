package com.example.improvedmuseumartgallery.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import com.example.improvedmuseumartgallery.domain.useCase.SearchArtwork
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO make the states common and generic

@HiltViewModel
class SearchViewModel @Inject constructor (
    private val museumRepository: MuseumRepository,
    private val searchArtworkUseCase: SearchArtwork
) :
    ViewModel() {

    // TODO for searchText why we use shared flow instead of state flow to get the data
    // TODO full difference between state flow and shared flow
    private val refreshSearch = MutableSharedFlow<String>()

    val searchV2 = refreshSearch.transform { query ->
        emit(UiState.Loading)
        val result = searchArtworksV2(query)
        emit(result)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Success(emptyList()))

    fun searchV2(query: String) {
        viewModelScope.launch {
            refreshSearch.emit(query)
        }
    }

    private suspend fun searchArtworksV2(query: String): UiState<List<Int>> {
        return try {
            val result = searchArtworkUseCase(query)
            UiState.Success(result.objectIDs ?: listOf())
        } catch (e: Exception) {
            UiState.Error
        }
    }

}