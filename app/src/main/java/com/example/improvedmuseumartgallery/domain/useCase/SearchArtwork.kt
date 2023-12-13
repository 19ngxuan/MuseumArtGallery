package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import javax.inject.Inject

class SearchArtwork @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend operator fun invoke(query: String): SearchResponse {
        return museumRepository.searchArtworks(query)
    }
}