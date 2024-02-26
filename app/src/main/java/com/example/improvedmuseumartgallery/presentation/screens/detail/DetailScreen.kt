package com.example.improvedmuseumartgallery.presentation.screens.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.improvedmuseumartgallery.domain.model.ArtworkUi
import com.example.improvedmuseumartgallery.presentation.dimensions.dimens.normalPadding
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import com.example.improvedmuseumartgallery.presentation.screens.common.ArtworkTitle
import com.example.improvedmuseumartgallery.presentation.screens.common.BackButton
import com.example.improvedmuseumartgallery.presentation.screens.common.DownloadButton
import com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents.ArtworkCard
import com.example.improvedmuseumartgallery.presentation.screens.detail.detailScreenComponents.BookMarkCard
import com.example.improvedmuseumartgallery.presentation.screens.error.ErrorScreen
import com.example.improvedmuseumartgallery.presentation.screens.loading.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun DetailScreen(
    detailViewModel: DetailViewModel,
    navigateToFavorites: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDownload: () -> Unit

) {
    val detailUiState by detailViewModel.detailUiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(90.dp),
                title = { },
                navigationIcon = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart

                    ) {
                        BackButton(onclick = {navigateToSearch()})
                    }
                },
                colors = topAppBarColors(
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
                    IconButton(onClick = { navigateToDownload() }) {
                        Icon(
                            Icons.Filled.Download,
                            contentDescription = "Localized description"
                        )
                    }

                }
            }
        }

    ) { contentPadding ->

        Column(modifier = Modifier.padding(contentPadding)) {


            when (val uiState = detailUiState) {
                is UiState.Loading -> {
                    LoadingScreen()
                }

                is UiState.Success -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        DetailContent(
                            detailViewModel,
                            artDetails = uiState.data,
                            onBookmarkClick = { detailViewModel.setBookmark(!uiState.data.isBookmarked) },
                            navigateToDownload
                        )
                    }
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
    detailViewModel: DetailViewModel,
    artDetails: ArtworkUi,
    onBookmarkClick: () -> Unit,
    navigateToDownload: () -> Unit


    ) {

    val scrollStateArtwork = rememberScrollState()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(state = scrollStateArtwork)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ArtworkTitle(title = artDetails.title)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(normalPadding)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),

                contentAlignment = Alignment.Center
            ) {

                Column(modifier = Modifier.verticalScroll(state = scrollState)) {
//                    ArtworkCard(
//                        image = artDetails.primaryImage,
//                        isChecked = false,
//                        onCheckClick = {}
//                    )
//                    Spacer(modifier = Modifier.size(8.dp))
                    for (imageWithCheck in artDetails.additionalImages) {
                        ArtworkCard(image = imageWithCheck.imageUrl,
                            isChecked = imageWithCheck.isCheckedForDownload,
                            onCheckClick = {
                                detailViewModel.toggleCheckbox(
                                    imageWithCheck.imageUrl,
                                    imageWithCheck.isCheckedForDownload
                                )
                                imageWithCheck.isCheckedForDownload =
                                    !imageWithCheck.isCheckedForDownload
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                ArtworkDescription()
//            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                val context = LocalContext.current
                Row {
                    DownloadButton {

                        context.getExternalFilesDir(null)
                            ?.let {
                                detailViewModel.downloadImages(artDetails.isCheckedList, it) {
                                Toast.makeText(context,"Images Downloaded",Toast.LENGTH_LONG).show()
                                navigateToDownload() }
                            }


                    }
                    Button(onClick = { detailViewModel.cancelDownload() }) {
                        Text("Cancel")


                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column {
                    LinearProgressIndicator(
                        progress = { artDetails.downloadProgress },
                    )
                    val percentage = artDetails.downloadProgress * 100


                    Text("${percentage}%")
                }
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BookMarkCard(
                    isBookmarked = artDetails.isBookmarked,
                    onBookmarkClick = onBookmarkClick
                )


            }
        }

    }

}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO


)
@Composable
fun DetailPreview() {

}




