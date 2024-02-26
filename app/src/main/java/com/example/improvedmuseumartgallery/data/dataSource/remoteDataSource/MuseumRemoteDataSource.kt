package com.example.improvedmuseumartgallery.data.dataSource.remoteDataSource

import com.example.improvedmuseumartgallery.data.network.museumAPI.MuseumApiService
import com.example.improvedmuseumartgallery.data.network.okHttp.FileDownloader
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.domain.model.SearchResponse
import java.io.File
import javax.inject.Inject

class MuseumRemoteDataSource @Inject constructor(
    private val museumApiService: MuseumApiService,
    private val fileDownloaderService: FileDownloader
) {
    suspend fun searchArtworks(query: String): SearchResponse {
        return museumApiService.searchArtworks(query)
    }

    suspend fun getArtwork(artworkId: Int): Artwork {
        return museumApiService.getArtwork(artworkId)
    }

    suspend fun downloadFile(url: String, file: File): Result<Unit> {
        return fileDownloaderService.downloadFile(url, file)
    }


}