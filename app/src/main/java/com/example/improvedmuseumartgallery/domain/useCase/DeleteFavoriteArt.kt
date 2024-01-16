package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import javax.inject.Inject

class DeleteFavoriteArt @Inject constructor(
    private val museumRepository: MuseumRepository
) {
    suspend operator fun invoke(favoriteArtwork: FavoriteArtwork) {
        return museumRepository.deleteFavoriteArt(favoriteArtwork)
    }
}

