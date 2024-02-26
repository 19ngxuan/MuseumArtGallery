package com.example.improvedmuseumartgallery.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.domain.useCase.SearchArtwork
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtworkUseCase: SearchArtwork,
) :
    ViewModel() {


    private val queryFlow = MutableSharedFlow<String>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchedIdsFlow = queryFlow.flatMapLatest { query ->
        searchArtworkUseCase(query).transform {
            emit(UiState.Loading)
            emit(UiState.Success(it))
        }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        UiState.Success(emptyList())
    )

    fun inputQuery(query: String) {
        viewModelScope.launch {
            queryFlow.emit(query)
        }
    }




}