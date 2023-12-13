package com.example.improvedmuseumartgallery.domain.repository

import com.example.improvedmuseumartgallery.data.dataSource.MuseumRemoteDataSource
import com.example.improvedmuseumartgallery.data.network.MuseumApiService
import com.example.improvedmuseumartgallery.data.repository.MuseumRepositoryImp
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
    fun provideMuseumRemoteDataSource(apiService: MuseumApiService): MuseumRemoteDataSource {
        return MuseumRemoteDataSource(apiService)
    }
    @Provides
    @Singleton
    fun provideMuseumRepository(
        remoteDataSource: MuseumRemoteDataSource
    ): MuseumRepository {
        return MuseumRepositoryImp(remoteDataSource)
    }
}