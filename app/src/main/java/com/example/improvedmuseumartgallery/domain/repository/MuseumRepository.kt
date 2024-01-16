package com.example.improvedmuseumartgallery.domain.repository

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import kotlinx.coroutines.flow.Flow

interface MuseumRepository {
    suspend fun getArtwork(artworkId: Int): Artwork
    suspend fun searchArtworks(query: String): List<CheckedItem>?
    suspend fun insertFavoriteArt(favoriteArtwork: FavoriteArtwork)
    suspend fun deleteFavoriteArt(favoriteArtwork: FavoriteArtwork)
    fun getFavoriteArtworkById(objectID: Int): Flow<FavoriteArtwork?>
    fun getAllFavoriteArtworkIds(): Flow<List<Int>>

}