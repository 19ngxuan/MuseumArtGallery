package com.example.improvedmuseumartgallery.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.improvedmuseumartgallery.MuseumApplication
import com.example.improvedmuseumartgallery.data.MuseumRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface SearchUiState {
    data class Success(val artworkIds:List<Int>) : SearchUiState
    object Error : SearchUiState
    object Loading : SearchUiState
}

class SearchViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    var searchUiState: SearchUiState by mutableStateOf(SearchUiState.Loading)
        private set

    fun searchArtworks(query: String) {
        viewModelScope.launch {
            searchUiState = SearchUiState.Loading
            try {
                searchUiState= SearchUiState.Success(museumRepository.searchArtworks(query).objectIDs?: listOf())


            } catch (e: IOException) {
                searchUiState = SearchUiState.Error
            } catch (e: HttpException) {
                searchUiState = SearchUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MuseumApplication)
                val museumRepository = application.container.museumRepository
                SearchViewModel(museumRepository = museumRepository)
            }
        }
    }
}