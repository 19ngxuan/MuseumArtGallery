package com.example.improvedmuseumartgallery.presentation.screens.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.model.ArtworkUi
import com.example.improvedmuseumartgallery.domain.model.ArtworkWithFavorite
import com.example.improvedmuseumartgallery.domain.model.ImageWithDownloadCheck
import com.example.improvedmuseumartgallery.domain.useCase.DeleteFavoriteArt
import com.example.improvedmuseumartgallery.domain.useCase.DownloadFile
import com.example.improvedmuseumartgallery.domain.useCase.GetArtwork
import com.example.improvedmuseumartgallery.domain.useCase.GetFavoriteArtworkById
import com.example.improvedmuseumartgallery.domain.useCase.InsertFavoriteArt
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Thread.sleep
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArtworkUseCase: GetArtwork,
    private val insertFavoriteArtUseCase: InsertFavoriteArt,
    private val deleteFavoriteArtUseCase: DeleteFavoriteArt,
    private val getFavoriteArtworkByIdUseCase: GetFavoriteArtworkById,
    private val downloadFileUseCase: DownloadFile,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    @OptIn(ExperimentalCoroutinesApi::class)
    private val artworkFlow = savedStateHandle.getStateFlow<String?>("artId", null)
        .flatMapLatest { artId ->
            getArtworks(artId?.toIntOrNull() ?: 0)
        }

    private val _isCheckedListFlow = MutableStateFlow(emptyList<String>())
    private val _downloadProgress = MutableStateFlow(0f)

    val detailUiState =
        combineTransform(
            artworkFlow,
            _isCheckedListFlow,
            _downloadProgress
        ) { artwork, isCheckedList, downloadProgress ->
            emit(UiState.Loading)

            val imagesWithCheck = artwork.additionalImages.map { image ->

                ImageWithDownloadCheck(image, isCheckedList.contains(image))
            }
            val result = UiState.Success(
                ArtworkUi(
                    artwork.objectID,
                    artwork.title,
                    artwork.artistDisplayName,
                    artwork.primaryImage,
                    imagesWithCheck,
                    artwork.department,
                    artwork.isBookmarked,
                    isCheckedList,
                    downloadProgress
                )
            )
            emit(result)

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)


    private suspend fun getArtworks(artId: Int): Flow<ArtworkWithFavorite> {
        return getArtworkUseCase(artId)
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


    private fun addToCheckedList(imageUrl: String) {

        if (!_isCheckedListFlow.value.contains(imageUrl)) {
            _isCheckedListFlow.value = _isCheckedListFlow.value + imageUrl
        }
    }

    private fun removeFromCheckedList(imageUrl: String) {

        if (_isCheckedListFlow.value.contains(imageUrl)) {
            _isCheckedListFlow.value = _isCheckedListFlow.value.filter { it != imageUrl }
        }
    }

    fun toggleCheckbox(targetString: String, currentStatus: Boolean) {

        if (_isCheckedListFlow.value.contains(targetString)) {
            removeFromCheckedList(targetString)
            Log.d("ViewModel", "Removing artwork from Checklist current status: $currentStatus")

        } else {
            addToCheckedList(targetString)
            Log.d("ViewModel", "Add artwork to Checklist current status:$currentStatus")
        }

    }


    private suspend fun saveFavoriteArt() {
        val artworkUiState = detailUiState.value
        if (artworkUiState is UiState.Success) {
            val favoriteArtwork = artworkUiState.data.toFavoriteArtwork()
            insertFavoriteArtUseCase(favoriteArtwork)
        }
    }

    private suspend fun deleteFavoriteArt() {
        val artworkUiState = detailUiState.value
        if (artworkUiState is UiState.Success) {
            val favoriteArtwork = artworkUiState.data.toFavoriteArtwork()
            deleteFavoriteArtUseCase(favoriteArtwork)

        }
    }


    private val scope = viewModelScope
    private var supervisorJob: Job = SupervisorJob()


    fun downloadImages(urls: List<String>, directory: File,onDownloadComplete: () -> Unit) {
        scope.launch(supervisorJob) {
            urls.forEachIndexed { index, url ->
                val fileName = url.substringAfterLast('/')


                downloadFileUseCase(url, File(directory, fileName))

                val progress = ((index + 1) / urls.size.toFloat())
                _downloadProgress.value = progress

                Log.d("ViewModel", "Images Downloaded.")
                sleep(3000)
            }
            onDownloadComplete()
        }
    }

    fun cancelDownload() {
        supervisorJob.cancel()
        _downloadProgress.value = 0f
        Log.d("ViewModel", "Images Download canceled.")
    }

}


fun ArtworkUi.toFavoriteArtwork(): FavoriteArtwork {
    return FavoriteArtwork(
        objectID = objectID,
        title = title,
        artistDisplayName = artistDisplayName,
        department = department,
        primaryImage = primaryImage,
    )
}