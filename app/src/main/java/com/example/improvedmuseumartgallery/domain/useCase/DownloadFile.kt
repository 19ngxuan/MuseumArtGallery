package com.example.improvedmuseumartgallery.domain.useCase

import com.example.improvedmuseumartgallery.domain.repository.MuseumRepository
import java.io.File
import javax.inject.Inject

class DownloadFile @Inject constructor(
    private val museumRepository: MuseumRepository
) {

    suspend operator fun invoke(url: String, file: File) {
        museumRepository.downloadFile(url, file)
    }

}