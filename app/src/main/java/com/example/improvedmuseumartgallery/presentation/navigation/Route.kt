package com.example.improvedmuseumartgallery.presentation.navigation

sealed class Route (val route: String) {

    data object SearchScreen: Route(route = "searchScreen")
    data object DetailScreen: Route(route = "detailScreen")
    data object FavoritesScreen: Route(route = "favoriteScreen")
    data object FavoritesDetailScreen: Route(route = "favoriteDetailScreen")
    data object DownloadScreen: Route(route = "downloadScreen")



}