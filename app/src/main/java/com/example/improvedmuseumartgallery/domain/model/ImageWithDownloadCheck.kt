package com.example.improvedmuseumartgallery.domain.model

data class ImageWithDownloadCheck(
    val imageUrl: String,
    var isCheckedForDownload: Boolean
)
