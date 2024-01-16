package com.example.improvedmuseumartgallery.data.dataSource.localDataSource

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.dao.FavoriteArtworkDao
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MuseumLocalDataSource @Inject constructor(
    private val favoriteArtworkDao: FavoriteArtworkDao
) {

    suspend fun insertFavoriteArt(favoriteArtwork: FavoriteArtwork) {
        return favoriteArtworkDao.insertFavoriteArt(favoriteArtwork)
    }

    suspend fun deleteFavoriteArt(favoriteArtwork: FavoriteArtwork) {
        return favoriteArtworkDao.deleteFavoriteArt(favoriteArtwork)
    }

    fun getFavoriteArtworkById(objectID: Int): Flow<FavoriteArtwork?> {
        return favoriteArtworkDao.getFavoriteArtworkById(objectID)
    }

    fun getAllFavoriteArtworkIds(): Flow<List<Int>> {
        return favoriteArtworkDao.getAllFavoriteArtworkIds()
    }


}