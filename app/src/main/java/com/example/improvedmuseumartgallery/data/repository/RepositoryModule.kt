package com.example.improvedmuseumartgallery.data.repository

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.MuseumLocalDataSource
import com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource.MuseumRemoteDataSource
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideMuseumRepository(
        remoteDataSource: MuseumRemoteDataSource,
        localDataSource: MuseumLocalDataSource
    ): MuseumRepository {
        return MuseumRepositoryImp(remoteDataSource, localDataSource)
    }
}