package com.example.improvedmuseumartgallery.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val objectIDs: List<Int>?,
    val total: Int?,

)
