package com.example.improvedmuseumartgallery.data

import com.example.improvedmuseumartgallery.BuildConfig
import com.example.improvedmuseumartgallery.network.MuseumApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val museumRepository: MuseumRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL =
        "https://collectionapi.metmuseum.org/public/collection/v1/"

    private val loggerInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }
    private val client = OkHttpClient.Builder().addInterceptor(loggerInterceptor).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: MuseumApiService by lazy {
        retrofit.create(MuseumApiService::class.java)
    }

    override val museumRepository: MuseumRepository by lazy {
        NetworkMuseumRepository(retrofitService)
    }
}