package com.example.improvedmuseumartgallery.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artwork(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    @SerialName(value = "primaryImage")
    val primaryImage: String,
    val additionalImages: List<String>,
    val department: String
)
