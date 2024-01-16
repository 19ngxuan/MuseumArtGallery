package com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource


import com.example.improvedmuseumartgallery.data.network.MuseumApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideMuseumRemoteDataSource(apiService: MuseumApiService): MuseumRemoteDataSource {
        return MuseumRemoteDataSource(apiService)
    }

}