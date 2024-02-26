package com.example.improvedmuseumartgallery.domain.repository

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.model.ArtworkWithFavorite
import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MuseumRepository {
    suspend fun getArtwork(artworkId: Int): Flow<ArtworkWithFavorite>
    suspend fun searchArtworks(query: String): Flow<List<CheckedItem>>
    suspend fun insertFavoriteArt(favoriteArtwork: FavoriteArtwork)
    suspend fun deleteFavoriteArt(favoriteArtwork: FavoriteArtwork)
    fun getFavoriteArtworkById(objectID: Int): Flow<FavoriteArtwork?>
    fun getAllFavoriteArtworkIds(): Flow<List<Int>>
    suspend fun downloadFile(url: String, file: File) :Result<Unit>

}