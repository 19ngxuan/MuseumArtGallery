package com.example.improvedmuseumartgallery.presentation.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.improvedmuseumartgallery.presentation.screens.common.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    navigateToFavoriteDetail: (Int) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToDownload: () -> Unit,
    navigateToSearch: () -> Unit,

    ) {

    val favoriteIds by favoritesViewModel.favoriteArtworkIdListFlow.collectAsState()



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

    ) { contentPadding ->
        LazyColumn(modifier = Modifier.padding(contentPadding)) {
            items(favoriteIds) { artId ->
                ListItem(headlineContent = { Text("$artId") }, Modifier.clickable {
                    navigateToFavoriteDetail(artId)
                })
            }
        }
    }


}

