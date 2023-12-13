package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import javax.inject.Inject

class GetArtwork @Inject constructor (
    private val museumRepository: MuseumRepository
){
    suspend operator fun invoke(artId: Int): Artwork {
        return museumRepository.getArtwork(artId)
    }
}
