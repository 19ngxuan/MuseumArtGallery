package com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.improvedmuseumartgallery.R
import com.example.improvedmuseumartgallery.data.dataSource.localDataSource.entity.FavoriteArtwork
import com.example.improvedmuseumartgallery.presentation.screens.common.BackButton
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteDetailScreen(
    favoriteDetailViewModel: FavoriteDetailViewModel,
    navigateToFavorites: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDownload: () -> Unit
) {
    val artworkFlow: Flow<FavoriteArtwork?> = favoriteDetailViewModel.favoriteArtwork
    val artwork: FavoriteArtwork? by artworkFlow.collectAsState(initial = null)



    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(90.dp),
                title = {},
                navigationIcon = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart

                    ) {
                        BackButton(onclick = {navigateToSearch()})
                    }
                }
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
                    IconButton(onClick = { navigateToDownload() }) {
                        Icon(
                            Icons.Filled.Download,
                            contentDescription = "Localized description"
                        )
                    }
                }
            }
        }

    ) {
        Column {


            artwork?.let { it1 ->
                FavoriteDetailContent(

                    artDetails = it1

                )
            }


        }
    }


}

@Composable
fun FavoriteDetailContent(
    artDetails: FavoriteArtwork,

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
            contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.size(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 64.dp)
        ) {
            Text(
                text = artDetails.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "Künstler: ${artDetails.artistDisplayName}")
            Text(text = "Abteilung: ${artDetails.department}")
        }
    }
}