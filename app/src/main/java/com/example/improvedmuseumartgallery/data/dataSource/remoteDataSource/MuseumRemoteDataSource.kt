package com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource

import com.example.improvedmuseumartgallery.data.network.MuseumApiService
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import javax.inject.Inject

class MuseumRemoteDataSource @Inject constructor(
    private val museumApiService: MuseumApiService
) {
    suspend fun searchArtworks(query: String): SearchResponse {
        return museumApiService.searchArtworks(query)
    }

    suspend fun getArtwork(artworkId: Int): Artwork {
        return museumApiService.getArtwork(artworkId)
    }

}