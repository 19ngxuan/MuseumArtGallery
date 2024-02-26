package com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteArtworks")
data class FavoriteArtwork(
    @PrimaryKey(autoGenerate = true)
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val primaryImage: String,
    val department: String,
    var isBookmarked: Boolean = true
)

