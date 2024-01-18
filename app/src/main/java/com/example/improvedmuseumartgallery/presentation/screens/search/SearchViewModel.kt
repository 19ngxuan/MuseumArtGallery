package com.example.improvedmuseumartgallery.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.domain.useCase.SearchArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtworkUseCase: SearchArtwork,
) :
    ViewModel() {


    private val queryFlow = MutableSharedFlow<String>()

//    val searchedIdFlow = queryFlow.transform { query ->
//        emit(UiState.Loading)
//        val result = getArtworkIds(query)
//        emit(result)
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Success(emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchedIdsFlow = queryFlow.flatMapLatest { query ->
        searchArtworkUseCase(query).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
    }

    fun inputQuery(query: String) {
        viewModelScope.launch {
            queryFlow.emit(query)
        }
    }

//    private suspend fun getArtworkIds(query: String): UiState<List<CheckedItem>> {
//        return try {
//            val result = searchArtworkUseCase(query)
//            UiState.Success(result ?: listOf())
//        } catch (e: Exception) {
//            UiState.Error
//        }
//    }


}