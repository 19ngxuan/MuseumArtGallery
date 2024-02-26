package com.example.improvedmuseumartgallery.domain.model


data class ArtworkUi(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val primaryImage: String,
    val additionalImages: List<ImageWithDownloadCheck>,
    val department: String,
    var isBookmarked: Boolean,
    val isCheckedList: List<String>,
    val downloadProgress: Float
)
