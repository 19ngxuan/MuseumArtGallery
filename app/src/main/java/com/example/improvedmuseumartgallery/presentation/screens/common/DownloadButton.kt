package com.example.improvedmuseumartgallery.presentation.screens.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DownloadButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Download")
    }
}