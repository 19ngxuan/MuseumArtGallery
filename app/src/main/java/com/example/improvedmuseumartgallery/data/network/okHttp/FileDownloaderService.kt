package com.example.improvedmuseumartgallery.data.network.okHttp

import java.io.File

interface FileDownloader {
    suspend fun downloadFile(url: String, file: File): Result<Unit>
}

