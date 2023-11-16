package com.example.improvedmuseumartgallery.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val objectIDs: List<Int>?,
    val total: Int?
)
