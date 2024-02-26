package com.example.improvedmuseumartgallery.presentation.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun BookmarkButton(
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit
) {

    IconButton(onClick = onBookmarkClick) {
        Icon(
            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = if (isBookmarked) "Remove Bookmark" else "Add Bookmark"
        )
    }
}
