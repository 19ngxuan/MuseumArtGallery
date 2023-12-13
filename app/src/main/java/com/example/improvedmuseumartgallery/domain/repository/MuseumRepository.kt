package com.example.improvedmuseumartgallery.domain.repository

import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse

interface MuseumRepository {
    suspend fun getArtwork(artworkId: Int): Artwork
    suspend fun searchArtworks(query: String): SearchResponse
}