package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteArtworkIds @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        return museumRepository.getAllFavoriteArtworkIds()
    }

}