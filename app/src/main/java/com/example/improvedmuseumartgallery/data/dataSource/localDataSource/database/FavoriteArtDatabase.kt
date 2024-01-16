package com.example.improvedmuseumartgallery.data.dataSource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.dao.FavoriteArtworkDao
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork

@Database(entities = [FavoriteArtwork::class], version = 1, exportSchema = false)
abstract class FavoriteArtDatabase : RoomDatabase() {
    abstract fun favoriteArtworkDao(): FavoriteArtworkDao
}