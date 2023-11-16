package com.example.improvedmuseumartgallery.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.improvedmuseumartgallery.viewModel.SearchUiState
import com.example.improvedmuseumartgallery.viewModel.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navigateToDetail: (Int) -> Unit) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var itemsHistory = remember { mutableStateListOf("") }
    val uiState = searchViewModel.searchUiState

    val onSearch: (String) -> Unit = { query ->
        if(query.isNotEmpty()) {
            searchViewModel.searchArtworks(query)

        }
    }

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                onSearch(text)
                itemsHistory.add(text)
                active = false
                text = ""
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search Icon"
                )
            },
            trailingIcon = {

                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close, contentDescription = "Close Icon"
                    )
                }
            }
        ) {
            itemsHistory.forEach {
                Row(modifier = Modifier.padding(all = 14.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "History Icon"
                    )
                    Text(text = it)
                }


            }


        }

        when (uiState) {
            is SearchUiState.Loading -> {
                LoadingScreen()
            }

            is SearchUiState.Success -> {
                LazyColumn {
                    items(uiState.artworkIds) { artId ->
                        ListItem(headlineContent = { Text("$artId") }, Modifier.clickable {
                            navigateToDetail(artId)

                        })
                    }
                }
            }

            is SearchUiState.Error -> {
                Text("Fehler beim Laden der Suchergebnisse", color = Color.Red)
            }
        }
    }

}