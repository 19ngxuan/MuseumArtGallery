package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArtworkById @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    operator fun invoke(objectId: Int): Flow<FavoriteArtwork?> {
        return museumRepository.getFavoriteArtworkById(objectId)
    }
}
