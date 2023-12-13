package com.example.improvedmuseumartgallery.data.repository

import com.example.improvedmuseumartgallery.data.network.MuseumApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
        fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @Provides
    @Singleton
    fun provideMuseumApiService(retrofit: Retrofit): MuseumApiService =
        retrofit.create(MuseumApiService::class.java)
}


