package com.example.improvedmuseumartgallery.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun DetailScreen(detailViewModel: DetailViewModel) {
    val uiState = detailViewModel.detailUiState.collectAsState()

    //TODO Check what is LaunchEffect and the why the key that we passed is important
    // LaunchedEffect starts new Coroutine
    // Observes changes in artId
    // If key(artId) changes -> restart Coroutine block

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val uiState = uiState.value) {
            is UiState.Loading -> {
                LoadingScreen()
            }

            is UiState.Success -> {
                DetailScreenContent(artDetails = uiState.data)
            }

            else -> {
                ErrorScreen(retryAction = {})
            }


        }
    }
}

@Composable
fun DetailScreenContent(artDetails: Artwork) {
    Box(
        modifier = Modifier
            .size(500.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(artDetails.primaryImage)
                .build(),
            contentDescription = stringResource(R.string.art_picture),

            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),


            )
    }
    Spacer(modifier = Modifier.size(16.dp))

    Text(
        text = "Titel: ${artDetails.title}",
        style = MaterialTheme.typography.headlineMedium
    )
    // Weitere Details anzeigen
    Text(text = "Künstler: ${artDetails.artistDisplayName}")
    Text(text = "Abteilung: ${artDetails.department}")
}

