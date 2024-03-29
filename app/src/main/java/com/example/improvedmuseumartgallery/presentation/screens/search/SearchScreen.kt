package com.example.improvedmuseumartgallery.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.improvedmuseumartgallery.domain.model.CheckedItem
import com.example.improvedmuseumartgallery.presentation.screens.UiState
import com.example.improvedmuseumartgallery.presentation.screens.loading.AnimatedLoadingBuffer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetail: (Int) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToDownload: () -> Unit
) {

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val statesOfIdListsFlow = searchViewModel.searchedIdsFlow
    val stateOfIdLists by statesOfIdListsFlow.collectAsState(initial = UiState.Success(emptyList()))


    val onSearch: (String) -> Unit = { query ->
        if (query.isNotEmpty()) {
            searchViewModel.inputQuery(query)

        }
    }


    Scaffold(

        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* doSomething() */ }) {
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

    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.size(24.dp))
            Box(
                modifier = Modifier
                    .padding(8.dp)

            ) {

                Text(
                    text = "What are you looking for?",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = Bold,
                        fontFamily = MaterialTheme.typography.headlineMedium.fontFamily
                    )

                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    onSearch(text)
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
            ) {/*ItemsHistory*/
                // Items History
            }

            when (stateOfIdLists) {
                is UiState.Loading -> {
                    AnimatedLoadingBuffer()
                }

                is UiState.Success -> {

                    LazyColumn {
                        items((stateOfIdLists as UiState.Success<List<CheckedItem>>).data) { artId ->
                            ListItem(headlineContent = {
                                Text(
                                    text = "${artId.id}",
                                    color = if (artId.isFavorite) Color.Blue else Color.Unspecified
                                )
                            },
                                Modifier.clickable {
                                    navigateToDetail(artId.id)
                                }
                            )
                        }
                    }

                }

                else -> {
                    Text("Error in Loading Data")
                }
            }


        }
    }

}

