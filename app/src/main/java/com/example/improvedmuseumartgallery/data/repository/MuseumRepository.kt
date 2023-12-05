package com.example.improvedmuseumartgallery.data.repository

import com.example.improvedmuseumartgallery.data.dataSource.MuseumRemoteDataSource
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import javax.inject.Inject





class MuseumRepositoryImp @Inject constructor(
    private val museumRemoteDataSource: MuseumRemoteDataSource
) : MuseumRepository {

    override suspend fun searchArtworks(query: String): SearchResponse {
        return museumRemoteDataSource.searchArtworks(query)
    }

    override suspend fun getArtwork(artworkId: Int): Artwork {

        return museumRemoteDataSource.getArtwork(artworkId)
    }

}