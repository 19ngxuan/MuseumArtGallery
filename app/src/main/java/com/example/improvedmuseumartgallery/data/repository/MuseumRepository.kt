package com.example.improvedmuseumartgallery.data.repository

import com.example.improvedmuseumartgallery.data.network.MuseumApiService
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import javax.inject.Inject





class NetworkMuseumRepository @Inject constructor(
    private val museumApiService: MuseumApiService
) : MuseumRepository {

    override suspend fun searchArtworks(query: String): SearchResponse {
        return museumApiService.searchArtworks(query)
    }

    override suspend fun getArtwork(artworkId: Int): Artwork {

        return museumApiService.getArtwork(artworkId)
    }

}