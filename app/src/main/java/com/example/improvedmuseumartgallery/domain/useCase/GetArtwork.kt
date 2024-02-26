package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.model.ArtworkWithFavorite
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtwork @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend operator fun invoke(artId: Int): Flow<ArtworkWithFavorite> {
        return museumRepository.getArtwork(artId)
    }
}
