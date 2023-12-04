package com.example.improvedmuseumartgallery.data.network

import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumApiService {
    @GET("objects/{objectID}")
    suspend fun getArtwork(@Path("objectID") objectID: Int): Artwork

    @GET("search")
    suspend fun searchArtworks(@Query("q") query: String): SearchResponse
}