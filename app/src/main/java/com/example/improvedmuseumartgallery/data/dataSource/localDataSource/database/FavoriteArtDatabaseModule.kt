package com.example.improvedmuseumartgallery.data.dataSource.localDataSource.database

import android.content.Context
import androidx.room.Room
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.dao.FavoriteArtworkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteArtDatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteArtDatabase(@ApplicationContext appContext: Context): FavoriteArtDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteArtDatabase::class.java,
            "favorite_artwork_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteArtworkDao(favoriteArtDatabase: FavoriteArtDatabase): FavoriteArtworkDao =
        favoriteArtDatabase.favoriteArtworkDao()


}