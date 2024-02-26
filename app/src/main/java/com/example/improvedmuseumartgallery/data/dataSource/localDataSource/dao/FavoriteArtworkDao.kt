package com.example.improvedmuseumartgallery.data.dataSource.localDataSource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArtworkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteArt(favoriteArtwork: FavoriteArtwork)

    @Delete
    suspend fun deleteFavoriteArt(favoriteArtwork: FavoriteArtwork)

    @Query("SELECT * FROM favoriteArtworks Where objectID = :objectID LIMIT 1")
    fun getFavoriteArtworkById(objectID: Int): Flow<FavoriteArtwork?>

    @Query("SELECT objectID FROM favoriteArtworks")
    fun getAllFavoriteArtworkIds(): Flow<List<Int>>

}