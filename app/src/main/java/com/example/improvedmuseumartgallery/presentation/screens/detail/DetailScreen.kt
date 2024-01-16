package com.example.improvedmuseumartgallery.presentation.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ViewCarousel
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.improvedmuseumartgallery.R
import com.example.improvedmuseumartgallery.domain.model.Artwork
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import com.example.improvedmuseumartgallery.presentation.screens.error.ErrorScreen
import com.example.improvedmuseumartgallery.presentation.screens.loading.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun DetailScreen(
    detailViewModel: DetailViewModel,
    navigateToFavorites: () -> Unit,
    navigateToSearch: () -> Unit
) {
    val detailUiState = detailViewModel.detailUiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        Icon(
                            imageVector = Icons.Default.ViewCarousel,
                            contentDescription = "Menu",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },

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
                }
            }
        }

    ) {

        Column {

            when (val uiState = detailUiState.value) {
                is UiState.Loading -> {
                    LoadingScreen()
                }

                is UiState.Success -> {
                    DetailContent(
                        artDetails = uiState.data.artwork,
                        isBookmarked = uiState.data.isFavored,
                        onBookmarkClick = { detailViewModel.setBookmark(!uiState.data.isFavored) },
                    )
                }

                else -> {
                    ErrorScreen(retryAction = {})
                }
            }
        }


    }


}

@Composable
fun DetailContent(
    artDetails: Artwork,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(artDetails.primaryImage)
                .build(),
            contentDescription = stringResource(R.string.art_picture),

            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            modifier = Modifier
                .weight(2.5f)
                .fillMaxSize(),
            contentScale = Crop


        )

        Spacer(modifier = Modifier.size(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 64.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column {
                    Row {
                        Text(
                            text = artDetails.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        BookmarkButton(
                            isBookmarked = isBookmarked,
                            onBookmarkClick = onBookmarkClick
                        )
                    }

                    Text(text = "Künstler: ${artDetails.artistDisplayName}")
                    Text(text = "Abteilung: ${artDetails.department}")
                }
            }
        }
    }


}

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


