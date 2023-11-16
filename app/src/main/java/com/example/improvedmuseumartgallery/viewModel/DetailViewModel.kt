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
import com.example.improvedmuseumartgallery.model.Artwork
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface DetailUiState {
    data class Success(val artDetail: Artwork) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
    private set

    fun getArt(artId: Int) {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {


                DetailUiState.Success(museumRepository.getArtwork(artId))
            } catch (e: IOException) {
                DetailUiState.Error

            } catch (e: HttpException) {
                DetailUiState.Error

            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MuseumApplication)
                val museumRepository = application.container.museumRepository
                DetailViewModel(museumRepository = museumRepository)
            }
        }
    }
}