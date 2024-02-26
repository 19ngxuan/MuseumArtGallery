package com.example.improvedmuseumartgallery.presentation.screens.download

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.improvedmuseumartgallery.presentation.screens.common.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DownloadScreen(
    navigateToFavorites: () -> Unit,
    navigateToSearch: () -> Unit

) {
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.height(90.dp), title = { }, navigationIcon = {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart

            ) {
                BackButton(onclick = {navigateToSearch()})
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
        )
    },

        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { navigateToSearch() }) {
                        Icon(Icons.Filled.Home, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { navigateToFavorites() }) {
                        Icon(
                            Icons.Filled.BookmarkBorder,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.Download, contentDescription = "Localized description"
                        )
                    }

                }
            }
        }

    ) { contentPadding ->
        val imagesDir = LocalContext.current.getExternalFilesDir(null)
        val imageFiles = imagesDir?.listFiles { file ->
            file.isFile && file.name.endsWith(".jpg", ignoreCase = true)
        }

//        LazyColumn(modifier = Modifier.padding(contentPadding)) {
//            if (imageFiles != null) {
//                items(imageFiles.toList()) { file ->
//                    Image(
//                        modifier = Modifier.size(100.dp),
//                        painter = rememberAsyncImagePainter(model = file),
//                        contentDescription = "Bilddatei"
//                    )
//                    Log.d("Download", "Bild wird geladen")
//                }
//            }

        if (imageFiles != null) {

            val columns = GridCells.Fixed(3)

            LazyVerticalGrid(
                columns = columns,
                modifier = Modifier.padding(contentPadding)
            ) {
                items(imageFiles.toList()) { file ->
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = rememberAsyncImagePainter(model = file),
                        contentDescription = "bilddatei"
                    )
                    Log.d("Download", "Bild wird geladen")
                }
            }
        }

    }
}

