package com.example.improvedmuseumartgallery.data.dataSource.localDataSource

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.dao.FavoriteArtworkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MuseumLocalDataSourceModule {

    @Provides
    @Singleton
    fun provideMuseumLocalDataSource(favoriteArtworkDao: FavoriteArtworkDao): MuseumLocalDataSource {
        return MuseumLocalDataSource(favoriteArtworkDao)
    }

}