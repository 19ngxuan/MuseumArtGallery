package com.example.improvedmuseumartgallery.domain.model


data class ArtworkWithFavorite(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val primaryImage: String,
    val additionalImages: List<String>,
    val department: String,
    var isBookmarked: Boolean

)
