package com.example.improvedmuseumartgallery.data

import com.example.improvedmuseumartgallery.model.Artwork
import com.example.improvedmuseumartgallery.model.SearchResponse
import com.example.improvedmuseumartgallery.network.MuseumApiService

interface MuseumRepository {
    suspend fun getArtwork(artworkId: Int): Artwork
    suspend fun searchArtworks(query: String): SearchResponse

}

class NetworkMuseumRepository(
    private val museumApiService: MuseumApiService
) : MuseumRepository {

    override suspend fun searchArtworks(query: String): SearchResponse {
        return museumApiService.searchArtworks(query)
    }

    override suspend fun getArtwork(artworkId: Int): Artwork {

        return museumApiService.getArtwork(artworkId)
    }

}