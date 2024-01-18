package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchArtwork @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend operator fun invoke(query: String): Flow<List<CheckedItem>> {
        return museumRepository.searchArtworks(query)
    }
}